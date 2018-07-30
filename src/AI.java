
public abstract class AI {
	Tile[][] current;
	int currentScore;
	public AI (Tile[][] current, int cS) {
		for (int i = 0; i < current.length; i++) {
			for (int j = 0; j < current.length; j++) {
				if (current[i][j] != null) this.current[i][j] = current[i][j];
			}
		}
		this.currentScore = cS;
	}
	
	public String nextMove() {
		return "";
	}
	
	public void update(Tile[][] newBoard, int cS) {
		for (int i = 0; i < current.length; i++) {
			for (int j = 0; j < current.length; j++) {
				this.current[i][j] = current[i][j];
			}
		}
		this.currentScore = cS;
	}
}
