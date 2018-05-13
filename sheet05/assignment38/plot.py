import matplotlib.pyplot as plt


def run(x0, a, iterations):
    results = [x0]
    for _ in range(iterations):
        x0 = a * x0 * (1 - x0)
        results.append(x0)
    return results


if __name__ == '__main__':
    x0_1, x0_2 = 0.228734167, 0.228734168
    a = 3.75
    results_1 = run(x0_1, a, 1000)
    results_2 = run(x0_2, a, 1000)
    diff = [a - b for a, b in zip(results_1, results_2)]

    plt.plot(results_1[100:120])
    plt.plot(results_2[100:120])
    plt.title('a = {}'.format(a))
    plt.show()
    plt.plot(diff[0:1000])
    plt.title('a = {}'.format(a))
    plt.show()
