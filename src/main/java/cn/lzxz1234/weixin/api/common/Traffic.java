package cn.lzxz1234.weixin.api.common;

public class Traffic {
    
    private long[] timestamps;
    private int index = 0;
    private long unitTime;

    public Traffic(long unitTime, int maxTraffic) {
        if (unitTime < 1L) 
            throw new IllegalArgumentException("Unit time must be greater than or equal 1ms!");
        if (maxTraffic < 1) 
            throw new IllegalArgumentException("Max traffic must be greater than or equal 1!");
        
        this.timestamps = new long[maxTraffic];
        this.unitTime = unitTime;
    }

    public long getUnitTime() {
        
        return this.unitTime;
    }

    public int getMaxTraffic() {
        
        return this.timestamps.length;
    }

    public synchronized void through() throws InterruptedException {
        
        through(1);
    }

    public synchronized void through(int traffic) throws InterruptedException {
        
        if (traffic < 1) 
            throw new IllegalArgumentException("Traffic must be greater than or equal 1!");
        
        if (traffic > this.timestamps.length) 
            throw new IllegalArgumentException("Traffic must be less than or equal max traffic limit[" + this.timestamps.length + "]!");
        
        long currentTimestamp = 0L;
        long millisDiff = 0L;
        while(true) {
            currentTimestamp = System.currentTimeMillis();
            millisDiff = currentTimestamp - this.timestamps[getPos(traffic - 1)];
            if (millisDiff >= this.unitTime) break;
            Thread.sleep(this.unitTime - millisDiff);
        }
        updateTimestamps(traffic, currentTimestamp);
    }

    public synchronized boolean tryThrough() {
        
        return tryThrough(1);
    }

    public synchronized boolean tryThrough(int traffic) {
        if (traffic < 1) 
            throw new IllegalArgumentException("Traffic must be greater than or equal 1!");
        if (traffic > this.timestamps.length) 
            throw new IllegalArgumentException("Traffic must be less than or equal max traffic limit["+ this.timestamps.length + "]!");
        
        long currentTimestamp = System.currentTimeMillis();
        long millisDiff = currentTimestamp - this.timestamps[getPos(traffic - 1)];
        if (millisDiff >= this.unitTime) {
            updateTimestamps(traffic, currentTimestamp);
            return true;
        }
        return false;
    }

    private void updateTimestamps(int traffic, long timestamp) {
        
        for (int i = 0; i < traffic; i++) 
            this.timestamps[getPos(i)] = timestamp;
        this.index = getPos(traffic);
    }

    private int getPos(int offset) {
        
        int remainder = (this.index + offset) % this.timestamps.length;
        return remainder < 0 ? remainder + this.timestamps.length : remainder;
    }
}