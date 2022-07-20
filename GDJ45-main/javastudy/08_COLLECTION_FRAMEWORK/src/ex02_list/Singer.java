package ex02_list;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Singer {

	private String name;
	private List<Song> songs;
	private Scanner sc;
	
	public Singer(String name) {
		this.name = name;
		songs = new ArrayList<Song>();
		sc = new Scanner(System.in);
	}
	
	public void addSong() {
		System.out.println("***노래추가***");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		songs.add(new Song(title, genre));
	}
	
	public void removeSongByObject() {
		System.out.println("***노래삭제***");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		songs.remove(new Song(title, genre));
	}
	
	public void removeSongByIndex() {
		System.out.println("***노래삭제***");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		for (int i = 0, size = songs.size(); i < size; i++) {
			Song song = songs.get(i);
			if(title.equals(song.getTitle()) && genre.equals(song.getGenre())) {
				songs.remove(i);
				return;
			}
		}
	}
	
	public void modifySong() throws RuntimeException {
		System.out.println("***노래수정***");
		System.out.print("노래번호(1~" + songs.size() + ") >>> ");
		int songNo = sc.nextInt();
		sc.nextLine();  // 엔터 제거
		if(songNo < 1 || songNo > songs.size())
			throw new RuntimeException("존재하지 않는 노래번호입니다.");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		System.out.print("장르 >>> ");
		String genre = sc.nextLine();
		songs.set(songNo - 1, new Song(title, genre));
	}
	
	public void findSong() throws RuntimeException {
		System.out.println("***노래조회***");
		if(songs.isEmpty())
			throw new RuntimeException("노래목록이 존재하지 않습니다.");
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		for(Song song : songs) {
			if(title.equals(song.getTitle())) {
				System.out.println("조회결과 " + song);
				return;
			}
		}
		System.out.println("제목이 [" + title + "]인 노래가 존재하지 않습니다.");
	}
	
	public void findAllSongs() throws RuntimeException {
		System.out.println("***전체노래조회***");
		System.out.println("가수 " + name);
		if(songs.isEmpty())
			throw new RuntimeException("노래목록이 존재하지 않습니다.");
		for(Song song : songs)
			System.out.println(song);
	}
	
	public void manage() {
		while(true) {
			try {
				System.out.print("1.추가 2.삭제 3.수정 4.조회 5.전체 0.종료 >>> ");
				int choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1: addSong(); break;
				case 2: removeSongByIndex(); break;  // 또는 removeSongByObject
				case 3: modifySong(); break;
				case 4: findSong(); break;
				case 5: findAllSongs(); break;
				case 0: System.out.println("프로그램 종료"); return;
				default: throw new RuntimeException("존재하지 않는 메뉴입니다.");
				}
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("선택 값은 정수입니다.");
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}