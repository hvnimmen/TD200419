package sample;

public enum EnemyType {

    Zombie("file:zombie-face.png");

    String fileName;

    EnemyType(String fileName){
        this.fileName = fileName;
    }

}
