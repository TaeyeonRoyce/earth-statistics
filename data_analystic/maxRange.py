from numpy import average
import pandas as pd
import matplotlib.pyplot as plt
import statistics as st
import numpy as np

plt.rc('font', family='AppleGothic')
plt.rcParams['axes.unicode_minus'] = False

pathName = '../statistics/wine_info.xlsx'

df = pd.read_excel(pathName, engine='openpyxl')
priceList = df.iloc[4:713, 5].values.tolist()
priceAverage = average(priceList)
priceMedian = st.median(priceList)

xRangeMin = 4000
xRangeMax = 250000
xRange = np.arange(xRangeMin, xRangeMax, 10000)

yRangeMin = 0
yRangeMax = 200

plt.subplot(4, 1, 1)
plt.suptitle("이마트 와인 가격 도수 분포표", fontsize=16)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=30, label='bins=30', rwidth=0.9)
plt.axvline(priceAverage, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()

plt.subplot(4, 1, 2)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=60, label='bins=60', color='orange', rwidth=0.9)
plt.axvline(priceAverage, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()

plt.subplot(4, 1, 3)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=90, label='bins=90', color='green', rwidth=0.9)
plt.axvline(priceAverage, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()

plt.subplot(4, 1, 4)
plt.xlim(xRangeMin, xRangeMax)
plt.ylim(yRangeMin, yRangeMax)
plt.xticks(xRange)
plt.hist(priceList, bins=30, label='bins=30', rwidth=0.9)
plt.hist(priceList, bins=60, label='bins=60', rwidth=0.9)
plt.hist(priceList, bins=90, label='bins=90', rwidth=0.9)
plt.axvline(priceAverage, color='r', linestyle='--',
            linewidth=1, label='average')
plt.axvline(priceMedian, color='black', linestyle='--',
            linewidth=1, label='median')
plt.legend()


plt.show()
