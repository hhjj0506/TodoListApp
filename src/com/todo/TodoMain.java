package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {
			
			case "help":
				Menu.displaymenu();
				break;
			
			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "find":
				String s = sc.next().trim();
				TodoUtil.findItem(l, s);
				break;
				
			case "find_cate":
				String s1 = sc.next().trim();
				TodoUtil.findCategory(l, s1);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("이름순으로 정렬하였습니다.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("이름역순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜순으로 정렬하였습니다.");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜역순으로 정렬하였습니다.");
				isList = true;
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정확한 명령어를 입력하세요. (도움말 - help)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
