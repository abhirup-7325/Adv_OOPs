#include <bits/stdc++.h>


using namespace std;


int main() {
    int n, m, k;
    cin >> n >> m >> k;

    vector<vector<vector<int>>> m1(n, vector<vector<int>>(m, vector<int>(k)));
    vector<vector<vector<int>>> m2(n, vector<vector<int>>(m, vector<int>(k)));

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (int l = 0; l < k; l++) {
                cin >> m1[i][j][l];
            }
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (int l = 0; l < k; l++) {
                cin >> m2[i][j][l];
            }
        }
    }


    vector<vector<vector<int>>> d1(n, vector<vector<int>>(m, vector<int>(k)));
    vector<vector<vector<int>>> d2(n, vector<vector<int>>(m, vector<int>(k)));
    vector<vector<vector<int>>> d12(n, vector<vector<int>>(m, vector<int>(k)));

    for (int i = 1; i < n; i++) {
        for (int j = 1; j < m; j++) {
            for (int l = 1; l < k; l++) {
                d1[i][j][l] = m1[i][j][l] - m1[i - 1][j][l];
                d2[i][j][l] = m2[i][j][l] - m2[i - 1][j][l];
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (int l = 0; l < k; l++) {
                d12[i][j][l] = m1[i][j][l] - m2[i][j][l];
            }
        }
    }
}