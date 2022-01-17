package com.target.barren.land;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BarrenLandAnalysis {
	
	
	private static final int WIDTH = 400;
	private static final int LENGTH = 600;
	private static Axis[][] axisGrid = new Axis[WIDTH][LENGTH];
	
	public String findLand(String[] inputAxis) {
		String STDOUT= "";
		List<Integer> goodLand = new LinkedList<>();
		List<Integer[]> badLandEndPoints= getBadLandAxis(inputAxis);

		List<Axis> totalBarrenLand = new LinkedList<>();
		
		for(Integer[] totalRectangle:badLandEndPoints ) {
        	System.out.println("Size:"+totalRectangle.length);

			totalBarrenLand.addAll(getTotalBarrenLand(totalRectangle));
			
		}
		  for (int y = 0; y < LENGTH; y++) {
	            for (int x = 0; x < WIDTH; x++) {
	                Axis co = new Axis(x, y);
//	                for each coordinate, if it's present in the totalBarrenLand list, mark that coordinate as barren and visited
	                for (Axis c : totalBarrenLand) {
	                    if (c.getX() == x && c.getY() == y) {
	                        co.setBarren(true);
	                        co.setVisited(true);
	                        break;
	                    } else {
	                        co.setBarren(false);
	                    }
	                }
	                axisGrid[x][y] = co;
	            }
	        }
		  
		goodLand = countFertileLand(goodLand,0,0);
		Collections.sort(goodLand);
		
		if(!goodLand.isEmpty()) {
			for(Integer lan: goodLand) {
				STDOUT+= lan.toString()+" ";
				
			}
			
		}else {
			STDOUT = "No Fertile land availabel";
		}
		return STDOUT;
		
	}
	
	private List<Integer> countFertileLand(List<Integer> goodLand, int i, int j) {
		// TODO Auto-generated method stub
		for(int y = j; y<LENGTH; y++) {
			for(int x =i; x<WIDTH; x++) {
				Axis grid = axisGrid[x][y];				
				if(!grid.isVisited()) {
					int totalGoodland = floodFill(axisGrid, x,y);
                    System.out.println("Total areafertile----"+totalGoodland);
					goodLand.add(totalGoodland);
					countFertileLand(goodLand,x,y);
				}
				
			}
			
		}
		return goodLand;
	}

	private int floodFill(Axis[][] grid, int x, int y) {
		int count =0;
		Stack<Axis> stack  = new Stack<Axis>();
		stack.push(new Axis(x,y));
		while (!stack.isEmpty()) {
			Axis as = stack.pop();
		
			//if the axis is unvisited then visit and make the counter by 1 and add the neighbors to  stack
			if(isAxisUnvisited(grid,as)) {
				count+=1;
				if(as.getY()-1>=0 && !grid[as.getX()][as.getY()-1].isVisited()) {
					stack.push(new Axis (as.getX(), as.getY()-1));
					
				}
				if(as.getY()+1 <LENGTH && !grid[as.getX()][as.getY()+1].isVisited()) {
					stack.push(new Axis (as.getX(), as.getY()+1));

				}
				if(as.getX()-1 >=0 && !grid[as.getX()-1][as.getY()].isVisited()) {
					stack.push(new Axis (as.getX()-1, as.getY()));
					
				}
				if(as.getX()+1 <WIDTH && !grid[as.getX()+1][as.getY()].isVisited()) {
					stack.push(new Axis (as.getX()+1, as.getY()));
				}
				
			}
			
		}
        System.out.println("Count : "+count);

		return count;
	}
    private static boolean isAxisUnvisited(Axis[][] grid, Axis as) {

//      Check that Axis as is not outside bounds of the grid
      if (as.getX() < 0 || as.getY() < 0 || as.getX() >= WIDTH || as.getY() >= LENGTH) {
          return false;
      }

      Axis axisToCheck = grid[as.getX()][as.getY()];

      if (axisToCheck.isVisited()) {
          return false;
      }

      axisToCheck.setVisited(true);

      return true;
  }

	private List<Axis> getTotalBarrenLand(Integer[] totalRectangle) {
		// TODO Auto-generated method stub 
		
		List<Axis> totalBarenLand = new LinkedList<>();
        System.out.println("1st dimension: "+totalRectangle[0] +" 2nd dimension: "+ totalRectangle[1]
        		+" 3rd dimension: "+totalRectangle[2]+ " 4th dimension: "+totalRectangle[3]);

		for(int i = totalRectangle[0]; i <=totalRectangle[2]; i++) {
			for(int j = totalRectangle[1]; j <= totalRectangle[3];j ++) {
				
				Axis axis = new Axis (i,j);
				totalBarenLand.add(axis);
				
			}
			
		}
		return totalBarenLand;
	}

	private List<Integer[]> getBadLandAxis(String[] inputAxis) {
		// TODO Auto-generated method stub
		List<Integer[]> landPoints = new LinkedList<Integer[]>();
		for(int i=0; i<inputAxis.length; i++) {
			
			String axisPoints []= inputAxis[i].split(" ");
	          System.out.println("input values size"+ axisPoints.length );

				Integer[] temp = new Integer[axisPoints.length];
				for(int j=0; j <axisPoints.length; j++) {
					temp[j]= Integer.parseInt(axisPoints[j]);
		          System.out.println("intRectangleCorner: "+ i+ "----"+axisPoints[j]);					
				}			
				landPoints.add(temp);
			}
			
		
		return landPoints;
	}

	public static void main(String args[]) {
		String STDIN[]= {"0 292 399 307"};
		 // String[] STDIN = {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"};

		BarrenLandAnalysis bla = new BarrenLandAnalysis();
	
		String STDOUT = bla.findLand(STDIN);
		System.out.println("STDOUT: "+ STDOUT);
		
	}

}
