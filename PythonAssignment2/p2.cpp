#include <bitsstdc++.h>

using namespace std;


int main() {
    int n, m;
    cin >> n >> m;

    vector<vector<int>> m1(n, vector<int>(m));
    vector<vector<int>> prefix(n + 1, vector<int>(m + 1, 0));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> m1[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            prefix[i + 1][j + 1] = m1[i][j] + prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j];
        }
    }

    return 0;
}