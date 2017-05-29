
public class MaxHeap2 {

	protected int size;
	protected Comparable[] mH;
	protected int position;

	/* Constructor */
	public MaxHeap2(int size) {
		this.size = size;
		mH = new Comparable[size + 1];
		position = 0;
	}

	/* get array of points and create maximum heap */
	public void createHeap(Point[] arr) {
		if (arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != null)
					insert(arr[i]);
			}
		}
	}

	/* returns an array of the points in the maximum heap */
	public Point[] getHeap() {
		Point[] a = new Point[position];
		for (int i = 1; i < position; i++)
			a[i] = mH[i].data;
		return a;
	}

	/* return the size of the heap */
	public int sizeOfHeap() {
		return position - 1;
	}

	/* insert a new point to the heap */
	public void insert(Point x) {
		if (position == 0) {
			mH[1] = new Comparable(x, 1);
			position = 2;
		} else {
			mH[position] = new Comparable(x, position);
			position++;
			bubbleUp();
		}
	}

	/* balance the BST to direction up and maintain the legality */
	public void bubbleUp() {
		int pos = position - 1;
		while (pos > 1 && mH[pos / 2].data.getY() < mH[pos].data.getY() || pos > 1
				&& (mH[pos / 2].data.getY() == mH[pos].data.getY() && mH[pos / 2].data.getX() < mH[pos].data.getX())) {
			Comparable y = new Comparable(mH[pos].data, pos);
			mH[pos] = new Comparable(mH[pos / 2].data, pos);
			mH[pos / 2] = new Comparable(y.data, pos / 2);
			pos = pos / 2;
		}

	}

	/* remove the maximum value and return it */
	public Point extractMax() {
		Point max = mH[1].data;
		mH[1] = mH[position - 1];
		mH[1].key = 1;
		mH[position - 1] = null;
		position--;
		sinkDown(1);
		return max;
	}

	/* return the maximum value */
	public Point getMax() {
		return mH[1].data;
	}

	public Comparable getMaxObject() {
		return mH[1];
	}

	/* balance the BST to direction down and maintain the legality */
	public void sinkDown(int k) {
		int largest = k;
		if (2 * k < position && mH[largest].data.getY() < mH[2 * k].data.getY()
				|| (2 * k < position && mH[largest].data.getY() == mH[2 * k].data.getY()
						&& mH[largest].data.getX() < mH[2 * k].data.getX())) {
			largest = 2 * k;
		}
		if (2 * k + 1 < position && mH[largest].data.getY() < mH[2 * k + 1].data.getY()
				|| (2 * k + 1 < position && mH[largest].data.getY() == mH[2 * k + 1].data.getY()
						&& mH[largest].data.getX() < mH[2 * k + 1].data.getX())) {
			largest = 2 * k + 1;
		}
		if (largest != k) {
			swap(k, largest);
			sinkDown(largest);
		}

	}

	public Comparable left(int i) {
		if (2 * i <= position)
			return mH[2 * i];
		else
			return null;

	}

	public Comparable right(int i) {
		if (((2 * i) + 1) <= position)
			return mH[2 * i + 1];
		else
			return null;

	}

	/* make a switch between to given values */
	public void swap(int a, int b) {
		Comparable temp = mH[a];
		mH[a] = mH[b];
		mH[a].key = a;
		mH[b] = temp;
		mH[b].key = b;

	}
	/* print the nodes in the heap */

	public void display() {
		for (int i = 1; i < mH.length; i++) {
			System.out.print(" " + mH[i].data + " key: " + mH[i].key);
		}
	}
}