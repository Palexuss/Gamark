class Unit {
    String name;
    float score;
    Unit(String name_in, float mark_in) {
        name = name_in;
        score = mark_in;
    }
    String displayInfo() {
        return name + "/" + score;
    }
    String getName() {
        return name;
    }
    float getScore() {
        return score;
    }
}
