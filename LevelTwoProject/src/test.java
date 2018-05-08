
public class test {
public static void main(String[] args) {
	int mouseX=0;
	int x=0;
	int mouseY=0;
	int y=0;
	int speedX=0;
	int speedY=0;
	if (mouseX < (x+25) && mouseX > (x-25) && mouseY < (y+25) && mouseY > (y-25)) {
                    if (mouseX < x && mouseY < y){
                            speedX = speedX + 5;
                            speedY = speedY + 5;
                    }
                    if (mouseX < x && mouseY > y) {
                            speedX += 5;
                            speedY = speedY -5;
                    }
                    if (mouseX > x && mouseY < y) {
                            speedX += -5;
                            speedY += 5;
                    }
                    if (mouseX > x && mouseY > y) {
                            speedX = speedY = -5;
                    }
                }
}
}
