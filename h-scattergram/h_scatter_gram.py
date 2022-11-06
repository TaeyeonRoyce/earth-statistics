import numpy as np


def auto_correlation_coefficient(one_dimension_data, lag):
    if lag == 0:
        print("Lag은 0보다 큰 자연수 이어야 합니다")
        return

    z = one_dimension_data[: -lag]
    z_lag = one_dimension_data[lag:]

    z_np = np.array(z)
    z_lag_np = np.array(z_lag)

    print(correlation_coefficient(z_np, z_lag_np))


def correlation_coefficient(data1, data2):
    n = len(data1)

    data1.reshape(n, 1)
    data2.reshape(n, 1)

    m = np.average(data1)
    slide_m = np.average(data2)

    z_std = np.sqrt(sum(pow(data1 - m, 2)))
    z_lag_std = np.sqrt(sum(pow(data2 - slide_m, 2)))

    co_std = sum(data1 * data2)
    bias = n * m * slide_m

    std = z_std * z_lag_std
    r = (co_std - bias) / std

    return round(r, 4)

