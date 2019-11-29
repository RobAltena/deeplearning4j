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
// Created by Yurii Shyrma on 12.12.2017
// @author Yurii Shyrma (iuriish@yahoo.com)
//

#include<ops/declarable/helpers/polyGamma.h>
#include<ops/declarable/helpers/zeta.h>
#include <NDArrayFactory.h>
#include <execution/Threads.h>

namespace nd4j {
namespace ops {
namespace helpers {


//////////////////////////////////////////////////////////////////////////
// implementation is based on serial representation written in terms of the Hurwitz zeta function as polygamma = (-1)^{n+1} * n! * zeta(n+1, x)
template <typename T>
static FORCEINLINE T polyGamma(T x) {

	const int xInt = static_cast<int>(x);

	if(x == xInt && x <= 0)
		return std::numeric_limits<T>::quiet_NaN();

	if(x - xInt == 0.5 && xInt <= 10) {		// psi(n+0.5) = -Euler_Mascheroni_const + -2*ln(2) + sum_from_k=1_to_n( 2/(2*k-1) )	, for n = 1,2,3,...inf, we use this formula only for n <= 10 to avoid time consuming sum calculation for bigger n
		for (uint i = 1; i < count; ++i) {
			/* code */
		}
	}

	// if (n < 0)
	// 	throw("polyGamma function: n must be >= 0 !");

	// if (x <= (T)0.)
	// 	throw("polyGamma function: x must be > 0 !");

	// TODO case for n = 0 (digamma)

	int sign = (n + 1) % 2  ?  -1 : 1;
	// T factorial = (T)std::tgamma(n + 1);

	return sign * getFactorial<T>(n) * zetaScalar<T>((T)(n + 1), x);
}


//////////////////////////////////////////////////////////////////////////
// calculate polygamma function for arrays
template <typename T>
static void polyGamma_(nd4j::LaunchContext * context, const NDArray& n, const NDArray& x, NDArray& output) {

	NDArray& result = output;

	int xLen = x.lengthOf();

	auto func = PRAGMA_THREADS_FOR {
        for (auto i = start; i < stop; i += increment) {
        	const T order = n.e<T>(i);
        	if(order != static_cast<int>(order))		// if order has fractional part then do not perform calculations and return NAN
        		result.p(i, std::numeric_limits<T>::quiet_NaN());
        	else
            	result.p(i, polyGammaScalar<T>(context, order, x.e<T>(i)));
        }
    };
	samediff::Threads::parallel_for(func, 0, x.lengthOf());
}

	void polyGamma(nd4j::LaunchContext * context, const NDArray& n, const NDArray& x, NDArray& output) {
		BUILD_SINGLE_SELECTOR(x.dataType(), polyGamma_, (context, n, x, output), FLOAT_TYPES);
	}

BUILD_SINGLE_TEMPLATE(template void polyGamma_, (nd4j::LaunchContext * context, const NDArray& n, const NDArray& x, NDArray& output), FLOAT_TYPES);



}
}
}

