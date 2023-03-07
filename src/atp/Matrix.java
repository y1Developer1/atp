package atp;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

	private List<List<Integer>> matrix = null;
	
	public Matrix() {
		this.initMatrixAsEmpty();
	}
	
	public Matrix(int num_of_rows, int num_of_columns){
		if(num_of_rows > 0 && num_of_columns > 0) {
			this.matrix = this.generateZero(num_of_rows, num_of_columns);
			return;
		}
		this.initMatrixAsEmpty();
	}
	
	public void initMatrixAsEmpty() {
		List<Integer> row = new ArrayList<Integer>();
		this.matrix = new ArrayList<List<Integer>>();
		this.matrix.add(row);
	}
	
	public List<List<Integer>> generateZero(int num_of_rows,int num_of_columns){
		if(num_of_rows < 1 || num_of_columns < 1) {
			return null;
		}
		List<List<Integer>> zero = new ArrayList<List<Integer>>();
		for(int i=0;i<num_of_rows;i++) {
			List<Integer> each_row = this.generateZeroRow(num_of_columns);
			zero.add(each_row);
		}
		return zero;
	}
	
	public List<Integer> generateZeroRow(int length){
		if(length < 1) {
			return null;
		}
		List<Integer> zero_row = new ArrayList<Integer>();
		for(int i=0;i<length;i++) {
			zero_row.add(0);
		}
		return zero_row;
	}
	
	public int getHeight() {
		return this.matrix.size();
	}
	
	public Integer getWidth() {
		Boolean equi_width = this.equiWidth();
		if(equi_width == null) {
			return null;
		}
		if(!equi_width) {
			return null;
		}
		return this.matrix.get(0).size();
	}
	
	public Boolean equiWidth() {
		List<Integer> first_row = this.matrix.get(0);
		if(first_row == null) {
			return null;
		}
		int first_width = first_row.size();
		int height = this.matrix.size();
		for(int i=1;i<height;i++) {
			List<Integer> each_row = this.matrix.get(i);
			if(each_row == null) {
				return null;
			}
			int each_width = each_row.size();
			if(each_width != first_width) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean definedAt(int row_no,int column_no) {
		if(row_no < 1 || column_no < 1) {
			return null;
		}
		Boolean equi_width = this.equiWidth();
		if(equi_width == null) {
			return null;
		}
		if(!equi_width) {
			return null;
		}
		int height = this.getHeight();
		Integer width = this.getWidth();
		if(row_no <= height && column_no <= width) {
			return true;
		}
		return false;
	}
	
	public void setValueAt(Integer value,int row_no,int column_no) {
		if(value == null) {
			return;
		}
		if(row_no < 1 || column_no < 1) {
			return;
		}
		Boolean defined = this.definedAt(row_no, column_no);
		if(defined == null) {
			return;
		}
		if(!defined) {
			return;
		}
		List<Integer> row = this.matrix.get(row_no-1);
		row.add(column_no-1, value);
	}
	
	public Integer getValueAt(int row_no,int column_no) {
		if(row_no < 1 || column_no < 1) {
			return null;
		}
		Boolean defined = this.definedAt(row_no, column_no);
		if(defined == null) {
			return null;
		}
		if(!defined) {
			return null;
		}
		List<Integer> row = this.matrix.get(row_no-1);
		return row.get(column_no-1);
	}
	
	public List<List<Integer>> copySubMatrixList(int from_row_no,int to_row_no,int from_column_no,int to_column_no) {
		int height = this.getHeight();
		if(from_row_no < 1 || from_row_no > height || from_row_no > to_row_no) {
			return null;
		}
		if(to_row_no < 1 || to_row_no > height) {
			return null;
		}
		Integer width = this.getWidth();
		if(width == null) {
			return null;
		}
		if(from_column_no < 1 || from_column_no > width || from_column_no > to_column_no) {
			return null;
		}
		if(to_column_no < 1 || to_column_no > width) {
			return null;
		}
		List<List<Integer>> new_rows = new ArrayList<List<Integer>>();
		for(int i=from_row_no;i<=to_row_no;i++) {
			List<Integer> each_row = this.matrix.get(i);
			List<Integer> new_row = new ArrayList<Integer>();
			for(int j=from_column_no;j<=to_column_no;j++) {
				Integer each_value = each_row.get(j);
				new_row.add(each_value);
			}
			new_rows.add(new_row);
		}
		return new_rows;
	}
	
	public Matrix getSubMatrix(int from_row_no,int to_row_no,int from_column_no,int to_column_no){
		int height = this.getHeight();
		if(from_row_no < 1 || from_row_no > height || from_row_no > to_row_no) {
			return null;
		}
		if(to_row_no < 1 || to_row_no > height) {
			return null;
		}
		Integer width = this.getWidth();
		if(width == null) {
			return null;
		}
		if(from_column_no < 1 || from_column_no > width || from_column_no > to_column_no) {
			return null;
		}
		if(to_column_no < 1 || to_column_no > width) {
			return null;
		}
		List<List<Integer>> submatrix_list = this.copySubMatrixList(from_row_no, to_row_no, from_column_no, to_column_no);
		if(submatrix_list == null) {
			return null;
		}
		int new_height = to_row_no - from_row_no + 1;
		int new_width = to_column_no - from_column_no + 1;
		Matrix submatrix = new Matrix(new_height,new_width);
		for(int i=1;i<=new_height;i++) {
			for(int j=1;j<=new_width;j++) {
				Integer copied_value = submatrix_list.get(i).get(j);
				submatrix.setValueAt(copied_value, i, j);
			}
		}
		return submatrix;
	}
	
	public void insertRowAt(int row_no,List<Integer> new_row) {
		if(row_no < 1) {
			return;
		}
		int height = this.getHeight();
		if(height + 1 < row_no) {
			return;
		}
		Integer width = this.getWidth();
		if(width == null) {
			return;
		}
		if(new_row == null) {
			return;
		}
		int new_row_width = new_row.size();
		if(new_row_width != width) {
			return;
		}
		List<List<Integer>> uppermatrix_list = this.copySubMatrixList(1, row_no-1, 1, width);
		if(uppermatrix_list == null) {
			return;
		}
		List<List<Integer>> new_matrix = new ArrayList<List<Integer>>();
		int upperheight = uppermatrix_list.size();
		for(int i=0;i<upperheight;i++) {
			List<Integer> sub_row = uppermatrix_list.get(i);
			new_matrix.add(sub_row);
		}
		new_matrix.add(new_row);
		if(row_no == height + 1) {
			return;
		}
		List<List<Integer>> lowermatrix_list = this.copySubMatrixList(row_no, height, 1, width);
		if(lowermatrix_list == null) {
			return;
		}
		int lowerheight = lowermatrix_list.size();
		for(int i=0;i<lowerheight;i++) {
			List<Integer> sub_row = lowermatrix_list.get(i);
			new_matrix.add(sub_row);
		}
	}
	
	
	
	public void insertColumnAt(int column_no,List<Integer> new_column) {
		if(column_no < 1) {
			return;
		}
		Integer width = this.getWidth();
		if(width == null) {
			return;
		}
		if(column_no > width) {
			return;
		}
		if(new_column == null) {
			return;
		}
		int new_height = new_column.size();
		int height = this.getHeight();
		if(new_height != height) {
			return;
		}
		List<List<Integer>> lefter_submatrix = this.copySubMatrixList(1, height, 1, column_no-1);
		if(lefter_submatrix == null) {
			return;
		}
		List<List<Integer>> new_matrix = new ArrayList<List<Integer>>();
		int lefter_width = column_no - 1;
		int lefter_height = lefter_submatrix.size();
		for(int i=0;i<lefter_height;i++) {
			for(int j=0;j<lefter_width;j++) {
				Integer each_value = lefter_submatrix.get(i).get(j);
				new_matrix.get(i).add(j, each_value);
			}
		}
		for(int i=0;i<height;i++) {
			new_matrix.get(i).add(column_no-1, new_column.get(i));
		}
		List<List<Integer>> righter_submatrix = this.copySubMatrixList(1, height, column_no, width);
		if(righter_submatrix == null) {
			return;
		}
		int righter_width = righter_submatrix.get(0).size();
		int righter_height = righter_submatrix.size();
		for(int i=0;i<righter_height;i++) {
			for(int j=0;j<righter_width;j++) {
				Integer each_value = righter_submatrix.get(i).get(j);
				new_matrix.get(i).add(column_no+j, each_value);
			}
		}
	}
	
	public List<Integer> getRowList(int row_no){
		if(row_no < 1) {
			return null;
		}
		int height = this.getHeight();
		if(row_no > height) {
			return null;
		}
		return this.matrix.get(row_no-1);
	}
	
	public List<Integer> getColumnList(int column_no){
		if(column_no < 1) {
			return null;
		}
		Integer width = this.getWidth();
		if(width == null) {
			return null;
		}
		if(column_no > width) {
			return null;
		}
		List<Integer> column = new ArrayList<Integer>();
		int height = this.getHeight();
		for(int i=1;i<=height;i++) {
			Integer value = this.getValueAt(i, column_no);
			column.add(value);
		}
		return column;
	}
	
	public Matrix plus(Matrix right_matrix) {
		if(right_matrix == null) {
			return null;
		}
		int left_height = this.getHeight();
		if(left_height == 0) {
			return null;
		}
		Integer left_width = this.getWidth();
		if(left_width == null || left_width == 0) {
			return null;
		}
		int right_height = right_matrix.getHeight();
		if(right_height == 0) {
			return null;
		}
		Integer right_width = right_matrix.getWidth();
		if(right_width == null || right_width == 0) {
			return null;
		}
		if(left_height != right_height) {
			return null;
		}
		int height = left_height;
		if(left_width != right_width) {
			return null;
		}
		int width = left_width;
		Matrix cal_result = new Matrix(height,width);
		for(int i=1;i<=height;i++) {
			for(int j=1;j<=width;j++) {
				Integer left_value = this.getValueAt(i, j);
				Integer right_value = right_matrix.getValueAt(i, j);
				Integer value = left_value + right_value;
				cal_result.setValueAt(value, i, j);
			}
		}
		return cal_result;
	}
	
	public Matrix times(Matrix right_matrix){
		if(right_matrix == null) {
			return null;
		}
		int left_height = this.getHeight();
		if(left_height == 0) {
			return null;
		}
		Integer left_width = this.getWidth();
		if(left_width == null) {
			return null;
		}
		if(left_width == 0) {
			return null;
		}
		int right_height = right_matrix.getHeight();
		if(right_height == 0) {
			return null;
		}
		Integer right_width = right_matrix.getWidth();
		if(right_width == null) {
			return null;
		}
		if(right_width == 0) {
			return null;
		}
		if(left_width != right_height) {
			return null;
		}
		Matrix cal_result = new Matrix(left_height,right_width);
		for(int i=1;i<=left_height;i++) {
			List<Integer> each_row = this.getRowList(i);
			if(each_row == null) {
				return null;
			}
			for(int j=1;j<=right_width;j++) {
				List<Integer> each_column = this.getColumnList(j);
				if(each_column == null) {
					return null;
				}
				Integer value = this.getInnerProduct(each_row, each_column);
				cal_result.setValueAt(value, i, j);
			}
		}
		return cal_result;
	}
	
	public Integer getInnerProduct(List<Integer> row,List<Integer> column) {
		if(row == null || column == null) {
			return null;
		}
		int width = row.size();
		if(width == 0) {
			return null;
		}
		int height = column.size();
		if(height == 0) {
			return null;
		}
		if(width != height) {
			return null;
		}
		int length = width;
		int value = 0;
		for(int i=0;i<length;i++) {
			Integer row_comp = row.get(i);
			Integer column_comp = column.get(i);
			if(row_comp == null || column_comp == null) {
				return null;
			}
			value += row_comp*column_comp;
		}
		return value;
	}
	
	public Matrix power(int n) {
		if(n < 0) {
			return null;
		}
		int height = this.getHeight();
		Integer width = this.getWidth();
		if(width == null) {
			return null;
		}
		if(height != width.intValue()) {
			return null;
		}
		if(n == 0) {
			Matrix zero = new Matrix(height,width.intValue());
			return zero;
		}
		if(n == 1) {
			return this;
		}
		Matrix powered_matrix = this;
		for(int i=2;i<=n;i++) {
			powered_matrix = this.times(powered_matrix);
		}
		return powered_matrix;
	}
	
}
