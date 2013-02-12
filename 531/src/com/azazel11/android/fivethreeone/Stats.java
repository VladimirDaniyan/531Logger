package com.azazel11.android.fivethreeone;

public class Stats {
	private int reps;
	private float weight;
	private float oneRm;
	
	public Stats(){
		reps = 0;
		weight = 0;
		oneRm = 0;
	}
	
	public Stats(int reps, float weight) {
		this.reps = reps;
		this.weight = weight;
	}

	// get reps performed
	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	// get weight used 
	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getOneRm() {
		if (reps == 1) {
			return weight;
		}
		return weight / (1.0278f - (0.0278f * reps));
	}

	public void setOneRm(float oneRm) {
		this.oneRm = oneRm;
	}
	
	

}
