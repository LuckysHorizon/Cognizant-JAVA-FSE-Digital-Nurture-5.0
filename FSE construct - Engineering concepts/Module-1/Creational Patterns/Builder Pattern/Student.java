public class Student {
    private String name;
    private int age;
    private String branch;
    private String college;
    
    private Student(StudentBuilder builder)
    {
        this.name = builder.name;
        this.age = builder.age;
        this.branch = builder.branch;
        this.college = builder.college;
    }
    public static class StudentBuilder
    {
        private String name;
        private int age;
        private String branch;
        private String college;
        public StudentBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }
        public StudentBuilder setBranch(String branch) {
            this.branch = branch;
            return this;
        }
        public StudentBuilder setCollege(String college) {
            this.college = college;
            return this;
        }
        public Student build()
        {
            return new Student(this);
        }
    }
    @Override
    public String toString() {
        return  name + " " + age + " " + branch + " " + college;
    }
    
}
