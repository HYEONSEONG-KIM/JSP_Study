package kr.or.ddit.vo;

import java.io.File;

public class FancyTreeNodeAdapter extends File implements FancyTreeNode{

	public FancyTreeNodeAdapter(File adaptee, String relativePath) {
		super(adaptee.getAbsolutePath());
		this.key = relativePath;
	}
	
	private String key;
	
	@Override
	public String getTitle() {
		return getName();
	}

	@Override
	public boolean isFolder() {
		return isDirectory();
	}

	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public int compareTo(File pathname) {
		boolean mine = isDirectory();
		boolean other = pathname.isDirectory();
		
		if(mine ^ other) {
			// 내가 폴더이면 오름차순
			return mine? -1 : 1; 
		}else {
			return super.compareTo(pathname);
		}
	}

	@Override
	public boolean isLazy() {
		return isFolder();
	}
}
















