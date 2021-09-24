package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, cate, due;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� �߰�]\n"
				+ "���� �Է� > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("�ߺ��� ������ �׸��� �ֽ��ϴ�.");
			return;
		}
		
		System.out.println("ī�װ� �Է� > ");
		cate = sc.next();
		
		sc.nextLine();
		System.out.println("���� �Է� > ");
		desc = sc.nextLine().trim();
		
		System.out.println("�������� �Է� (��/��/��) > ");
		due = sc.next();
		
		TodoItem t = new TodoItem(title, desc, cate, due);
		list.addItem(t);
		System.out.println("���ο� �׸��� �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]\n"
				+ "�׸� ��ȣ �Է� > ");
		int num = sc.nextInt();
		num = num - 1;
		String dec;
		
		TodoItem item = l.getList().get(num);
		System.out.println((num+1) + ". " + item.toString());
		System.out.println("�ش� �׸��� �����Ͻðڽ��ϱ�? (y/n) > ");
		dec = sc.next();
		
		if (dec.charAt(0) == 'y') {
			l.deleteItem(item);
			System.out.println("�׸��� ���ŵǾ����ϴ�.");
		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]\n"
				+ "�׸� ��ȣ �Է� > ");
		int num = sc.nextInt();
		num = num - 1;
		
		TodoItem item = l.getList().get(num);
		System.out.println((num+1) + ". " + item.toString());

		System.out.println("���ο� ���� �Է� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�ߺ��� ������ �׸��� �ֽ��ϴ�.");
			return;
		}
		
		System.out.println("���ο� ī�װ� �Է� > ");
		String new_category = sc.next().trim();
		
		sc.nextLine();
		System.out.println("���ο� ���� �Է� > ");
		String new_description = sc.nextLine().trim();
		
		sc.nextLine();
		System.out.println("���ο� �������� �Է� (��/��/��) > ");
		String new_due_date = sc.nextLine().trim();

		l.deleteItem(item);
		TodoItem t = new TodoItem(new_title, new_description, new_category ,new_due_date);
		l.addItem(t);
		System.out.println("�׸��� �����Ǿ����ϴ�.");
	}
	
	public static void findItem(TodoList l, String word) {
		int num = 1, count = 0;
		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(word) || item.getDesc().contains(word)) {
				System.out.println(num + ". " + item.toString());
				count++;
			}
			num++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void findCategory(TodoList l, String word) {
		int num = 1, count = 0;
		for (TodoItem item : l.getList()) {
			if (item.getCategory().contains(word)) {
				System.out.println(num + ". " + item.toString());
				count++;
			}
			num++;
		}
		System.out.println("�� " + count + "���� �׸��� ã�ҽ��ϴ�.");
	}
	
	public static void listCate(TodoList l) {
		Set<String> set = new HashSet<String>();
		
		for (TodoItem item : l.getList()) {
			set.add(item.getCategory());
		}
		
		System.out.println(set);
		System.out.println("�� " + set.size() + "���� ī�װ��� �����մϴ�.");
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� " + l.getList().size() + "��]");
		int count = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(count + ". " + item.toString());
			count++;
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("�׸���� ����Ǿ����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String line, title, desc, date, due, cate;
			int count = 0;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				cate = st.nextToken();
				title = st.nextToken();
				desc = st.nextToken();
				due = st.nextToken();
				date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, cate, due);
				t.setCurrent_date(date);
				l.addItem(t);
				count++;
			}
			br.close();
			System.out.println(count + "���� �׸���� �ҷ��Խ��ϴ�.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
