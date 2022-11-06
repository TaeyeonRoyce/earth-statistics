from h_scatter_gram import auto_correlation_coefficient


def get_one_dimension_data_set():
    z_1 = [3, 2, 4, 5, 6]
    # z_1 = [8, 6, 4, 3, 2, 3, 5, 5, 6, 6, 7, 8, 9]
    z_2 = [3, 7, 5, 8, 6, 8, 6, 9, 3, 6, 4, 5, 2]
    return [z_1, z_2]


if __name__ == '__main__':
    data_set = get_one_dimension_data_set()

    auto_correlation_coefficient(data_set[0], 1)

    # for data in data_set:
