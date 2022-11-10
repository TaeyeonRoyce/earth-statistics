import matplotlib.pyplot as plt
import numpy as np


def data_set():
    return [
        [3, 7, 155],
        [5, 6, 154],
        [3, 3, 130],
        [7, 2, 120],
        [3, -4, 77],
        [-6, -5, 49],
        [-3, -6, 50]
    ]


def get_euclidian_distance(a, b):
    x_1 = a[0]
    y_1 = a[1]
    x_2 = b[0]
    y_2 = b[1]
    return round(np.sqrt((x_1 - x_2) ** 2 + (y_1 - y_2) ** 2), 1)


def get_distance(data):
    distance = []
    for i in range(len(data)):
        single_distance = []
        standard_point = data[i]
        for j in range(i + 1, len(data)):
            euclidian_distance = get_euclidian_distance(standard_point, data[j])
            single_distance.append(euclidian_distance)

        distance.append(single_distance)

    return distance


def combine_by_h(distance_data, h):
    delta_h = 2.5

    data_combine_set = []
    for i in range(len(distance_data)):
        standard_distance = distance_data[i]
        for j in range(len(standard_distance)):
            if (h - delta_h <= distance_data[i][j] < h + delta_h):
                data_combine_set.append([i + 1, i + j + 2])

    return data_combine_set


def convert_to_value(combine_set, data_set):
    value_A = []
    value_B = []
    for set in combine_set:
        value_A.append(data_set[set[0] - 1][2])
        value_B.append(data_set[set[1] - 1][2])

    return [value_A, value_B]


def correlation_coefficient(data1, data2):
    n = len(data1)

    data1 = np.array(data1)
    data2 = np.array(data2)

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


def method_name(data_set, h):
    total_distance = get_distance(data_set)
    print(total_distance)
    combined_set = combine_by_h(total_distance, h)
    print(combined_set)
    value_set = convert_to_value(combined_set, data_set)
    coefficient = correlation_coefficient(value_set[0], value_set[1])
    print(coefficient)

    return coefficient


at_5 = method_name(data_set(), 5)
at_10 = method_name(data_set(), 10)
at_15 = method_name(data_set(), 15)

h = [5, 10, 15]
h_corr = [at_5, at_10, at_15]

plt.plot(h, h_corr, marker='s', color='r')
for i, v in enumerate(h):
    plt.xlabel("lag")
    plt.text(v, h_corr[i], h_corr[i],
             fontsize=9,
             color='blue',
             horizontalalignment='center',
             verticalalignment='center')

plt.show()
