/*******************************************************************************
 * Copyright (c) 2015-2018 Skymind, Inc.
 * Copyright (c) 2019 Konduit K.K.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

//
// @author Yurii Shyrma (iuriish@yahoo.com)
//

#include<ops/declarable/helpers/diGamma.h>
#include <execution/Threads.h>

namespace nd4j {
namespace ops {
namespace helpers {

//////////////////////////////////////////////////////////////////////////
// implementation is based on serial representation written in terms of the Hurwitz zeta function as polygamma = (-1)^{n+1} * n! * zeta(n+1, x)
template <typename T>
static T diGammaFunc(T x) {

	const int xInt = static_cast<int>(x);

	// negative and zero
	if(x <= 0) {
		if(x == xInt)	// integer
			return std::numeric_limits<T>::quiet_NaN();
		else
			return diGammaFunc<T>(1 - x) - M_PI / nd4j::math::nd4j_tan<T,T>(M_PI * x); // use reflection formula psi(1-x) = psi(x) + pi*cot(pi*x)
	}

	// positive integer
	if(x == xInt && xInt <= 20) {		// psi(n) = -Euler_Mascheroni_const + sum_from_k=1_to_n-1( 1/k ), for n = 1,2,3,...inf, we use this formula only for n <= 20 to avoid time consuming sum calculation for bigger n
		T result = -0.577215664901532;
		for (uint i = 1; i <= xInt - 1; ++i) {
			result += static_cast<T>(1) / i;
		}
		return result;
	}

	// positive half-integer
	if(x - xInt == 0.5 && xInt <= 20) {		// psi(n+0.5) = -Euler_Mascheroni_const - 2*ln(2) + sum_from_k=1_to_n( 2/(2*k-1) )	, for n = 1,2,3,...inf, we use this formula only for n <= 20 to avoid time consuming sum calculation for bigger n
		T result = -0.577215664901532 - 2 * nd4j::math::nd4j_log<T,T>(2);
		for (uint i = 1; i <= xInt; ++i) {
			result += static_cast<T>(2) / (2*i - 1);
		}
		return result;
	}

	// positive, smaller then 5, we should use number > 5 in order to have satisfactory accuracy in asymptotic expansion
	if(x < 5)
		return diGammaFunc<T>(1 + x) - static_cast<T>(1) / x;		 // recurrence formula  psi(x) = psi(x+1) - 1/x.

	// *** other positive **** //

	// truncated expansion formula (from wiki)
	// psi(x) = log(x) - 1/(2*x) - 1/(12*x^2) + 1/(120*x^4) - 1/(252*x^6) + 1/(240*x^8) - 5/(660*x^10) + 691/(32760*x^12) - 1/(12*x^14) + ...

	if(x >= sizeof(T) > 4 ? 1.e16 : 1.e8)		// if x is too big take into account only log(x)
		return nd4j::math::nd4j_log<T,T>(x);

	// coefficients used in truncated asymptotic expansion formula
	// -1/12, 1/120, -1/252, 1/240, -5/660, 691/32760, -1/12
	const T coeffs[7] = {-0.0833333333333333, 0.00833333333333333, -0.00396825396825397, 0.00416666666666667, -0.00757575757575758, 0.0210927960927961, -0.0833333333333333};

	const T x2Inv = static_cast<T>(1) / (x * x);
	T result = 0;
	for (uint i = 6; i >= 0; --i) {
		result = (result + coeffs[i]) * x2Inv;
	}
	return result + nd4j::math::nd4j_log<T,T>(x) - static_cast<T>(0.5) / x;
}


//////////////////////////////////////////////////////////////////////////
// calculate digamma function for array elements
template <typename T>
static void diGamma_(const NDArray& x, NDArray& z) {

	auto func = PRAGMA_THREADS_FOR {
        for (auto i = start; i < stop; i += increment)
            z.p(i, diGammaFunc<T>(x.e<T>(i)));
    };
	samediff::Threads::parallel_for(func, 0, x.lengthOf());
}

void diGamma(nd4j::LaunchContext* context, const NDArray& x, NDArray& z) {

	BUILD_SINGLE_SELECTOR(x.dataType(), diGamma_, (x, z), FLOAT_TYPES);
}

BUILD_SINGLE_TEMPLATE(template void diGamma_, (const NDArray& x, NDArray& z), FLOAT_TYPES);



}
}
}

