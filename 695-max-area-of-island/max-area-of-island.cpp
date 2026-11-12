class Solution {
public:
    int check(vector<vector<int>>& g,int i,int j){
        if(i==-1 || i==g.size() || j==-1 || j==g[0].size() || !g[i][j]) return 0;
        g[i][j]=0;
        return 1+check(g,i-1,j)+check(g,i,j-1)+check(g,i+1,j)+check(g,i,j+1);
    }
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int mx=0;
        for(int i=0;i<grid.size();i++){
            for(int j=0;j<grid[0].size();j++){
                if(grid[i][j]) mx = max(mx,check(grid,i,j));
            }
        }
        return mx;
    }
};