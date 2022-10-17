import matplotlib.pyplot as plt
import numpy as np
import math as math

plt.rc('font', family='AppleGothic')
plt.rcParams['axes.unicode_minus'] = False


def cal_compressibility(k_range):
    compressibility_list = []
    for k in k_range:
        x = get_x(k)
        max_transmission_code_size = x + 1
        bit_per_code = get_bit_amount_by(max_transmission_code_size)

        before_bits = k
        after_bits = (max_transmission_code_size + 1) * bit_per_code

        compressibility = (after_bits / before_bits) * 100  # 백분율로 변환
        compressibility_list.append(round(compressibility, 2))

    return compressibility_list


def get_x(k):
    # an = (n**2 + n + 2) / 2
    # n(n + 1) > 2 * k

    geo_average = int(math.sqrt(2 * k)) - 1
    x = geo_average
    while(x*(x + 1) <= 2 * k):
        x += 1

    return x


def get_bit_amount_by(max_transmission_code_size):
    bit_count = 0
    while(max_transmission_code_size > 0):
        max_transmission_code_size >>= 1
        bit_count += 1
    return bit_count


def pltBarChart(k_count, compressibility):
    x = np.arange(len(k_count))

    before_comp = compressibility[0]
    for i, v in enumerate(x):
        if (before_comp < compressibility[v]):
            plt.text(v, compressibility[i], "k = " + str(k_count[v]) + ", 압축률: " + str(compressibility[i]) + "%",
                     fontsize=9,
                     color="blue",
                     horizontalalignment='center',
                     verticalalignment='bottom')
            plt.axvline(i, color='r', linestyle='--',
                        linewidth=1, label='k')
        before_comp = compressibility[v]

    plt.ylim([0, 100])
    plt.title('이진수열의 길이와 압축률의 관계', fontsize=20)  # 타이틀 출력
    plt.xlabel('이진수열의 길이')  # x축 라벨 출력
    plt.ylabel('압축률')  # y축 라벨 출력
    plt.plot(x, compressibility)
    plt.xticks(x, k_count)
    plt.show()


if __name__ == "__main__":
    # k = 1의 개수
    # ex) 1111, k = 4
    k_list = range(100, 100000, 500)
    # k_list = [111, 1111, 11111]
    compressibility_list = cal_compressibility(k_list)

    pltBarChart(k_list, compressibility_list)
