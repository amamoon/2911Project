package game;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LevelGen {

	private static int gameMap[][];
	
	public LevelGen() {
	}
	
	public static void generate(int x, int y) {
		Random rand = new Random();
		gameMap = new int[x][y];
		for(int i = 0; i <= x - 1; i++) {
			gameMap[i][0] = 1;
			gameMap[i][y - 1] = 1;
			if(i >= 2 || i <= y - 2) {
				if(rand.nextBoolean()) gameMap[i][1] = 1;
				if(rand.nextBoolean()) gameMap[i][y - 2] = 1;
			}
		}
		for(int l = 0; l <= y - 1; l++) {
			//System.out.println(l + " " + 0 + " " + (x - 1) + " " + l);
			gameMap[0][l] = 1;
			gameMap[x - 1][l] = 1 ;
			if(l >= 2 || l <= y - 2) {
				if(rand.nextBoolean()) gameMap[1][l] = 1;
				if(rand.nextBoolean()) gameMap[x - 2][l] =  1;
			}
		}

		for(int k = 0; k < 2; k++) {
			int dx, dy, ex, ey;
			dx = dy = ex = ey = 0;
			while(dx == ex && dy == ey && (gameMap[dx][dy] != 3 || gameMap[ex][ey] != 2)) {
				dx = 2 + rand.nextInt((x-2) - 2 + 1);
				dy = 2 + rand.nextInt((y-2) - 2 + 1);
				ex = 3 + rand.nextInt((x-3) - 3 + 1);
				ey = 3 + rand.nextInt((y-3) - 3 + 1);
			}
			gameMap[dx][dy] = 3;
			gameMap[ex][ey] = 2;
		}
		int fx = 3 + rand.nextInt((x-3) - 3 + 1);
		int fy = 3 + rand.nextInt((y-3) - 3 + 1);
		
		while(true) {
			fx = 3 + rand.nextInt((x-3) - 3 + 1);
			fy = 3 + rand.nextInt((y-3) - 3 + 1);
			if(gameMap[fx][fy] == 3 || gameMap[fx][fy] == 2){
				fx = 3 + rand.nextInt((x-3) - 3 + 1);
				fy = 3 + rand.nextInt((y-3) - 3 + 1);
			}else{
				break;
			}
		}

		//String fileName = "./levelrand.txt";
		try {
			File file = new File("res/levels/30.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(x + " " + y + "\n");
			bw.write(fx + " " + fy + "\n");
			for(int i = 0; i < x; i++) {
				String string = "";
				for(int j = 0; j < y; j++) {
					string += gameMap[i][j];
					string += " ";
				}
				bw.write(string);
			}
			bw.close();
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
}
