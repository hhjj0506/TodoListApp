package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("--- TODO List ��ɾ� ���� ---");
        System.out.println("add - �׸��� �߰��մϴ�.");
        System.out.println("del - �׸��� �����մϴ�.");
        System.out.println("edit - �׸��� �����մϴ�.");
        System.out.println("ls - ����� ��� �׸���� ���Դϴ�.");
        System.out.println("ls_name_asc - ����� ��� �׸���� ��������� �����Ͽ� ���Դϴ�.");
        System.out.println("ls_name_desc - ����� ��� �׸���� ���񿪼����� �����Ͽ� ���Դϴ�.");
        System.out.println("ls_date - ����� ��� �׸���� ��¥������ �����Ͽ� ���Դϴ�.");
        System.out.println("exit - ���α׷��� �����մϴ�.");
    }

	public static void prompt() {
		System.out.println("\n��ɾ� �Է� > ");
	}
}
