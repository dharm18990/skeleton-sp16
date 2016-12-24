package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private boolean[][] grid;
	WeightedQuickUnionUF set;
	WeightedQuickUnionUF fullStatus;
	int noOfOpenSites;
	int noOfTotalEntries;
	
	public Percolation(int N){// create N-by-N grid, with all sites initially blocked
		grid=new boolean[N][N];
		int noOfTotalEntries=(int)(Math.pow(N+1,2)-1);
		set=new WeightedQuickUnionUF(noOfTotalEntries+2);
		fullStatus=new WeightedQuickUnionUF(noOfTotalEntries+1);
		noOfOpenSites=0;
		
	}               

	public void open(int row, int col){// open the site (row, col) if it is not open already
		if(grid[row][col]==false){
			grid[row][col]=true;
			noOfOpenSites++;	
		}
		if(row==0){
			set.union(noOfTotalEntries+1,xyTo1D(row,col));
			fullStatus.union(noOfTotalEntries+1,xyTo1D(row,col));
		}
		if(row==grid.length-1){
			set.union(noOfTotalEntries+2,xyTo1D(row,col));
		}
		
		if(row!=grid[row].length-1 && grid[row+1][col]==true){
			set.union(xyTo1D(row+1,col),xyTo1D(row,col));
			fullStatus.union(xyTo1D(row+1,col),xyTo1D(row,col));
		}
		
		if(row!=0 && grid[row-1][col]==true){
			set.union(xyTo1D(row-1,col),xyTo1D(row,col));
			fullStatus.union(xyTo1D(row-1,col),xyTo1D(row,col));
		}

		if(col!=grid[row].length-1 && grid[row][col+1]==true){
			set.union(xyTo1D(row,col+1),xyTo1D(row,col));
			fullStatus.union(xyTo1D(row,col+1),xyTo1D(row,col));
		}
		
		if(col!=0 && grid[row][col-1]==true){
			set.union(xyTo1D(row,col-1),xyTo1D(row,col));
			fullStatus.union(xyTo1D(row,col-1),xyTo1D(row,col));
		}


	}


	public boolean isOpen(int row, int col){// is the site (row, col) open?
		return grid[row][col];
	}

	public boolean isFull(int row, int col){// is the site (row, col) full?
		
		if(fullStatus.connected(noOfTotalEntries+1,xyTo1D(row,col))==true && isOpen(row,col))
			return true;
		return false;
		
	}

	public int numberOfOpenSites(){// number of open sites
		return noOfOpenSites;
	}

	public boolean percolates(){// does the system percolate?
		if(set.connected(noOfTotalEntries+1,noOfTotalEntries+2))
			return true;
		return false;
	}

	private int xyTo1D(int x,int y){
		return ((grid[x].length*x)+y);
	}

	public void display(){
		System.out.println("Display");
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid.length;j++){
				if(isOpen(i,j)){
					System.out.print(xyTo1D(i,j));
					System.out.println(" finding parent: "+set.find(xyTo1D(i,j)));	
				}
				
			}
			System.out.println();
		}
	}

	public static void main(String[] args){// unit testing (not required)
		Percolation perc=new Percolation(5);
		//perc.display();
	}   

}                       
