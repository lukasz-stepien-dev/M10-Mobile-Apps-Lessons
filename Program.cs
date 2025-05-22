using System;
using System.Collections.Generic;
using System.IO;

class Student
{
    public string Name { get; set; }
    public int Grade { get; set; }
}

class Program
{
    static void Main()
    {
        List<Student> students = new List<Student>();
        string[] lines = File.ReadAllLines("data.txt");
        int n = int.Parse(lines[0]);
        for (int i = 1; i <= n; i++)
        {
            string[] parts = lines[i].Split(' ');
            Student s = new Student
            {
                Name = parts[0],
                Grade = int.Parse(parts[1])
            };
            students.Add(s);
        }

        // Optional: print students to verify
        foreach (var student in students)
        {
            Console.WriteLine($"{student.Name} {student.Grade}");
        }
    }
}
