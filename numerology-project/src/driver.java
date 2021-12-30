import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class driver
{
	static <E> void printViaSll(String[] a) throws IOException 
	{
		String predictionsOutput = "";
		
		singlyLinkedList<Object> sll = new singlyLinkedList<>();
		
		for(int i = 0;i<a.length;i = i + 3)
		{
			numerology numeroSll = new numerology();
			
			numeroSll.month = (Integer.parseInt(a[i]));
			numeroSll.day = (Integer.parseInt(a[i+1]));
			numeroSll.year = (Integer.parseInt(a[i+2]));
			numeroSll.crunchPrediction();
			sll.addLast(numeroSll);
		}
		while(!(sll.isEmpty()))
		{
			numerology numSll = (numerology) sll.removeFirst();
			System.out.println(numSll.month+"/"+numSll.day+"/"+numSll.year+"---"+numSll.prediction);
			predictionsOutput = predictionsOutput + numSll.prediction+"\n";
		}
		fileCreateWrite("predict.txt",predictionsOutput);
	}			
			
	static void printViaDll(String[] a) 
	{
	doublyLinkedList<Object> dll = new doublyLinkedList<>();

		for(int i = 0;i<a.length;i = i + 3)
		{
			numerology numeroDll = new numerology();
			
			numeroDll.month = (Integer.parseInt(a[i]));
			numeroDll.day = (Integer.parseInt(a[i+1]));
			numeroDll.year = (Integer.parseInt(a[i+2]));
			numeroDll.crunchPrediction();
			dll.addFirst(numeroDll);
		}
		while(!(dll.isEmpty()))
		{
			numerology numDll = (numerology) dll.removeFirst();
			System.out.println(numDll.month+"/"+numDll.day+"/"+numDll.year+"---"+numDll.prediction);
		}
		
	}

	
	static String generateDates() throws IOException
	{	
		int month = 0;
		int day = 0;
		int year = 0;
		int[] date = new int[3];
		String combinedDates = "";
		
		Random rand = new Random();
		
		for(int i=0;i<100;i++) 
		{
			month = rand.nextInt(13);
			if(month==2) 
			{
				day = rand.nextInt(28);	
			}
			else if(month==4||month==6||month==9||month==11)
			{
				day = rand.nextInt(31);
			}
			else
			{				
				day = rand.nextInt(32);
			}
			year = (rand.nextInt(20-15) + 15) * 100 + rand.nextInt(100);
			date[0] = month;
			date[1] = day;
			date[2] = year;

			combinedDates = combinedDates+String.valueOf(month)+"-"+String.valueOf(day)+"-"+String.valueOf(year)+"\n";
		}
		return combinedDates;	
	}
	
	static String[] fileRead(String fileName) throws IOException
	{
		String[] result = new String[300];
		Scanner reader = new Scanner(new FileReader(fileName)).useDelimiter("-|\n");

		for(int i =0;i<result.length;++i)
		{
			if(reader.hasNextLine())
			{
				result[i] = reader.next();
			}
			else
			{
				break;
			}
		}
		reader.close();
		return result;
	}
	
	static void fileCreateWrite(String fileName,String data) throws IOException
	{
		File file = new File(fileName);
		if(file.exists())
		{
			file.delete();
		}
		else
		{
			file.createNewFile();
		}
		
		FileOutputStream os = null;
		os = new FileOutputStream(fileName,true);
		
		byte[] byteData1 = data.getBytes();
		byte[] byteData2 = "\n".getBytes();

		for(int i=0;i<byteData1.length;i++) 
		{
			os.write(byteData1[i]);
		}
		for(int i=0;i<byteData2.length;i++) 
		{
			os.write(byteData2[i]);
		}
		os.close();
	} 
	
	public static class singlyLinkedList<E>
	{
		
		
		public class Node<E>
		{
			private E element;
			private Node<E> next;
			
			public Node(E e, Node<E> n) 
			{
				element = e;
				next = n;
			}	
			public E getElement() 
			{
				return element;
			}
			public Node<E> getNext()
			{
				return next;
			}
			public void setNext(Node<E> n) 
			{
				next = n;
			}
		}
		
		private Node<E> head = null;
		private Node<E> tail = null;
		private int size = 0;
		
		public int size() 
		{
			return size;
		}
		public boolean isEmpty() 
		{
			return size == 0;
		}
		public E first()
		{
			if(isEmpty()) 
			{
				return null;
			}
			return head.getElement();
		}
		public E last()
		{
			if(isEmpty()) 
			{
				return null;
			}
			return tail.getElement();
		}
		public void addFirst(E e) 
		{
			head = new Node<E>(e,head);
			if(size == 0) 
			{
				tail = head;
			}	
			size++;
		}
		public void addLast(E e) 
		{
			Node<E> newest = new Node<>(e,null);
			if(isEmpty()) 
			{
				head = newest;
			}
			else
			{
				tail.setNext(newest);
			}
				tail = newest;
				size++;
		}
		public E removeFirst() 
		{
			if(isEmpty()) {return null;}
			E ans = head.getElement();
			head = head.getNext();
			size--;
			if(size == 0) 
			{
				tail = null;
			}
			return ans;
					
		}
		
		
		public singlyLinkedList() {}
	}
	
	public static class doublyLinkedList<E>
	{
		private static class Node<E>
		{
			private E element;
			private Node<E> prev;
			private Node<E> next;
			public Node(E e, Node<E> p, Node<E> n)
			{
				element = e;
				prev = p;
				next = n;
			}
			public E getElement(){return element;}
			public Node<E> getPrev() {return prev;}
			public Node<E> getNext() {return next;}
			public void setPrev(Node<E> p) {prev = p;}
			public void setNext(Node<E> n) {next = n;}
		}
		private Node<E> header;
		private Node<E> trailer;
		private int size = 0;
		
		public doublyLinkedList()
		{
			header = new Node<>(null,null,null);
			trailer = new Node<>(null,null,null);
			header.setNext(trailer);
		}
		public int size() 
		{
			return size = 0;
		}
		public boolean isEmpty() 
		{
			return size == 0;
		}
		public E first() 
		{
			if(isEmpty()) 
			{
				return null;
			}
			return header.getNext().getElement();
		}
		public E last() 
		{
			if(isEmpty())
			{
				return null;
			}
			return trailer.getPrev().getElement();
		}
		public void addFirst(E e) 
		{
			addBetween(e, header, header.getNext());
		}
		public E removeFirst()
		{
			if(isEmpty())
			{
				return null;	
			}
			return remove(header.getNext());
		}
		public E removeLast()
		{
			if(isEmpty())
			{
				return null;
			}
			return remove(trailer.getPrev());
		}
		private void addBetween(E e, Node<E> predecessor, Node<E> successor)
		{
			Node<E> newest = new Node<>(e,predecessor,successor);
			predecessor.setNext(newest);
			successor.setPrev(newest);
			size++;
		}
		private E remove(Node<E> node)
		{
			Node<E> predecessor = node.getPrev();
			Node<E> successor = node.getNext();
			predecessor.setNext(successor);
			successor.setPrev(predecessor);
			size--;
			return node.getElement();
		}
	}
	
	public static void main(String [] args) throws IOException
	{
		String[] monthDayYear = new String[300];

		fileCreateWrite("dates.txt",generateDates());
		monthDayYear = fileRead("dates.txt");
		
		System.out.println("These are printed Singly linked List traversing forward:");
		System.out.println("");
		printViaSll(monthDayYear);
		System.out.println("");
		System.out.println("These are printed from a Doubly linked List traversing backward:");
		System.out.println("");
		printViaDll(monthDayYear);	
	}
}	