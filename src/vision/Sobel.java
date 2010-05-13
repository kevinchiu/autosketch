package vision;

import java.util.ArrayList;

import processing.core.PImage;

public class Sobel {
	static double avgImg(int x1, int y1, int x2, int y2, float[][] pixelBuffer) {
		double total = 0;
		for (int i = y1; i < y2; i++)
			for (int j = x1; j < x2; j++)
				total += pixelBuffer[j][i];
		return total / ((x2 - x1) * (y2 - y1));
	}

	static double calcAngle(int x1, int y1, int x2, int y2, float[][] hbuff,
			float[][] vbuff) {

		double ret = Math.atan(avgImg(x1, y1, x2, y2, hbuff)
				/ avgImg(x1, y1, x2, y2, vbuff));
		if (String.valueOf(ret).equals("NaN"))
			ret = 0;
		return ret;

	}

	static int pointConvolution(int a1, int a2, int a3, int a4, int a5, int a6,
			int a7, int a8, int a9, int b1, int b2, int b3, int b4, int b5,
			int b6, int b7, int b8, int b9) {

		return a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4 + a5 * b5 + a6 * b6 + a7
				* b7 + a8 * b8 + a9 * b9;

	}

	public static ArrayList<Double> processImage(PImage img, int cells) {
		int c = cells;
		int width = img.width;
		int height = img.height;

		// calculate horizontal and vertical Sobel
		float[][] hb = new float[width][height];
		float[][] vb = new float[width][height];

		int[] p = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int[] h = { -1, -2, -1, 0, 0, 0, 1, 2, 1 };
		int[] v = { 1, 0, -1, 2, 0, -2, 1, 0, -1 };
		int[] pixels = img.pixels;
		for (int i = 1; i < height - 1; i++)
			for (int j = 1; j < width - 1; j++) {
				p[0] = pixels[(i - 1) * width + j - 1];
				p[1] = pixels[(i - 1) * width + j - 0];
				p[2] = pixels[(i - 1) * width + j + 1];
				p[3] = pixels[(i - 0) * width + j - 1];
				p[4] = pixels[(i - 0) * width + j - 0];
				p[5] = pixels[(i - 0) * width + j + 1];
				p[6] = pixels[(i + 1) * width + j - 1];
				p[7] = pixels[(i + 1) * width + j - 0];
				p[8] = pixels[(i + 1) * width + j + 1];
				hb[j][i] = (float) (pointConvolution(p[0], p[1], p[2], p[3],
						p[4], p[5], p[6], p[7], p[8], h[0], h[1], h[2], h[3],
						h[4], h[5], h[6], h[7], h[8]) / 8.0);
				vb[j][i] = (float) (pointConvolution(p[0], p[1], p[2], p[3],
						p[4], p[5], p[6], p[7], p[8], v[0], v[1], v[2], v[3],
						v[4], v[5], v[6], v[7], v[8]) / 8.0);
			}

		// derive signature from Sobel
		ArrayList<Double> signatureValues = new ArrayList<Double>();
		for (int i = 0; i < c; i++) {
			for (int j = 0; j < c; j++) {
				signatureValues.add(calcAngle(j * width / c, i * height
						/ c, (j + 1) * width / c, (i + 1) * height
						/ c, hb, vb));
			}
		}

		return signatureValues;
	}
}
