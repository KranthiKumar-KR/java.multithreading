package liveLock;

/**
 * project intelliJworkspace Created by kranthikumarpolimetla in package liveLock on 6/24/17.
 */
 class Worker {

    private String name;
    private boolean active;

     Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

     private String getName() {
        return name;
    }

     private boolean isActive() {
        return active;
    }

     synchronized void work(SharedPreference sharedPreference, Worker anotherThread) {
        while (active) {
            if (sharedPreference.getOwner() != this) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                continue;
            }
            if (anotherThread.isActive()) {
                System.out.println(name + " give the resource ownership to worker " +anotherThread.getName());
                sharedPreference.setOwner(anotherThread);
                continue;
            }

            System.out.println(name +" working on similar resource");
            active = false;
            sharedPreference.setOwner(anotherThread);
        }
    }


}
