package physics;

import java.util.ArrayList;

import entities.Particle;

public class ParticleBuffer {
	public static ArrayList<Particle> particles = new ArrayList<Particle>();
	public static void addParticle(Particle particle){
		particles.add(particle);
	}
}
