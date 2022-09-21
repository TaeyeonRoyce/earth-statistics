import pandas as pd
import matplotlib.pyplot as plt
import statistics as st
import numpy as np

plt.rc('font', family='AppleGothic')
plt.rcParams['axes.unicode_minus'] = False

pathName = '../statistics/wine_info.xlsx'

df = pd.read_excel(pathName, engine='openpyxl')
priceList = df.iloc[4:713, 5].values.tolist()
priceAverage = st.mean(priceList)
priceMedian = st.median(priceList)

xRangeMin = 4000
xRangeMax = 250000
xRange = np.arange(xRangeMin, xRangeMax, 10000)


box = plt.boxplot(priceList, notch=True, whis=1.5,
                  vert=False, sym="ro")

whiskers = [item.get_xdata() for item in box['whiskers']]
medians = [item.get_xdata() for item in box['medians']]
fliers = [item.get_xdata() for item in box['fliers']]

min = whiskers[0][1]
lowerQuratile = whiskers[0][0]
higherQuratile = whiskers[1][0]
max = fliers[0][-1]

plt.axvline(min, color='g', linestyle='--',
            linewidth=0.5, label='최솟값')
plt.text(min - 7000, 1.53, '최솟값\n= ' +
         str(int(min)), color='g', fontsize=10)

plt.axvline(lowerQuratile, color='b', linestyle='--',
            linewidth=0.5, label='제 1사분위')
plt.text(lowerQuratile - 7000, 1.53, '제 1사분위\n= ' +
         str(int(lowerQuratile)), color='b', fontsize=10)

plt.axvline(priceMedian, color='r', linestyle='--',
            linewidth=0.5, label='중앙값')
plt.text(priceMedian + 4000, 1.33, '중앙값\n= ' +
         str(int(priceMedian)), color='r', fontsize=10)

plt.axvline(higherQuratile, color='brown', linestyle='--',
            linewidth=0.5, label='제 3사분위')
plt.text(higherQuratile - 7000, 1.53, '제 3사분위\n= ' +
         str(int(higherQuratile)), color='brown', fontsize=10)


plt.xlabel("와인 가격", fontsize=16)
plt.legend(prop={'size': 18})
plt.xticks(xRange)

plt.show()
