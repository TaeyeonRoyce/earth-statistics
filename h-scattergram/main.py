import h_scatter_gram as h
import matplotlib.pyplot as plt


def get_one_dimension_data_set():
    # z_1 = [3, 2, 4, 5, 6]
    z_1 = [8, 6, 4, 3, 2, 3, 5, 5, 6, 6, 7, 8, 9]
    z_2 = [3, 7, 5, 8, 6, 8, 6, 9, 3, 6, 4, 5, 2]
    return [z_1, z_2]


if __name__ == '__main__':
    data_set = get_one_dimension_data_set()

    # for data in data_set:
    for i in range(1, 3):
        slided_data_set = h.slide_as_lag(data_set[1], i)
        correlation_coefficient = h.correlation_coefficient(
            slided_data_set[0], slided_data_set[1])
        plt.subplot(2, 1, i)
        plt.rc('font', family='AppleGothic')
        plt.rcParams['axes.unicode_minus'] = False

        plt.scatter(slided_data_set[0], slided_data_set[1])
        plt.plot([0, max(slided_data_set[0])],
                 [0, max(slided_data_set[0])])

        plt.title('lag = {}m, r = {}'.format(
            i * 5, correlation_coefficient))
    plt.suptitle("Z = {}".format(data_set[1]))
    plt.show()

# for data in data_set:
