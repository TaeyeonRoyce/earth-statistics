import numpy as np
from matplotlib import pyplot as plt


def data_set():
    return [
        [2, 7, 8.5],
        [5, 6, 7.2],
        [0, 3, 5.8],
        [7, 2, 4.1],
        [4, -3, 1.8],
        [-6, -2, 1.1],
        [-4, -5, 0.5]
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

def semi_variogram(value_set):
    sum_e = 0
    for i in range(len(value_set[0])):
        dif = value_set[0][i] - value_set[1][i]
        sum_e += dif ** 2
    div = (2 * (len(value_set[0])))
    return round(sum_e / div, 2)




# distance = get_distance(data_set())
#
# print(distance)
# combine_by_h = combine_by_h(distance, 15)
#
# print(combine_by_h)
# value_set = convert_to_value(combine_by_h, data_set())
# print(value_set)
# print(semi_variogram(value_set))

h = []
h_corr = []
distance = get_distance(data_set())
for i in range(1, 16):
    h.append(i)
    combine_by = combine_by_h(distance, i)
    value_set = convert_to_value(combine_by, data_set())
    h_corr.append(semi_variogram(value_set))

plt.plot(h, h_corr, marker='s', color='r')
for i, v in enumerate(h):
    plt.xlabel("lag")
    plt.text(v, h_corr[i], h_corr[i],
             fontsize=9,
             color='blue',
             horizontalalignment='center',
             verticalalignment='center')
plt.show()