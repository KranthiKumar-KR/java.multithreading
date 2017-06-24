package liveLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package liveLock on 6/24/17.
 */
 class SharedPreference {
    private Worker owner;

     SharedPreference(Worker owner) {
        this.owner = owner;
    }

     Worker getOwner() {
        return owner;
    }

     synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }
}
