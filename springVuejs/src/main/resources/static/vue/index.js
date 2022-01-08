new Vue({
    el: "#app",
    data() {
        return {

            students: [],
            popup: false,
            student: {
                id: "",
                firstName: "",
                lastName: "",
                dateBirthday: "",
                deleted: ""
            },
            button_edit: false,
            filter: '',
        }
    },
    methods: {
        openPopup() {
            this.popup = !this.popup;
        },
        getStudents: function() {
            axios
                .get("http://localhost:2022/api/student")
                .then(response => {
                    console.log(response.data);
                    this.students = response.data.students;
                }).catch(error => {
                    console.log(error)
                })

        },
        addStudent: function() {
            axios
                .post("http://localhost:2022/api/student/save/", this.student)
                .then(response => {
                    console.log(response.data);
                    this.openPopup();
                    alert('student Adding');
                    this.getStudents();
                    this.student.fistName = '';
                    this.student.lastName = '';
                    this.student.dateBirthday = '';

                }).catch(error => {
                    console.log(error);
                })
        },
        deleteStudent: function(student) {
            axios
                .get("http://localhost:2022/api/student/deleteStudent/" + student.id)
                .then(response => {
                    this.getStudents();
                    this.hasDeleted = false;
                }).catch(error => {
                    console.log(error);
                })
        },
        editStudent: function(student) {
            this.popup = !this.popup;
            this.button_edit = true;
            this.student = student;
        },
        updateStudent: function() {
            axios
                .post("http://localhost:2022/api/student/updateStudent/", this.student)
                .then(response => {
                    if (response.data.etat) {
                        this.popup = !this.popup;
                        alert("Student Update")
                        this.getStudents();
                        this.student.fistName = '';
                        this.student.lastName = '';
                        this.student.dateBirthday = '';
                    }
                }).catch(error => {
                    console.log(error);
                })
        }
    },
    computed: {
        filteredRows() {
            return this.students.filter(student => {
                const FN = student.firstName.toString().toLowerCase();
                const LN = student.lastName.toLowerCase();
                const searchTerm = this.filter.toLowerCase();
                return FN.includes(searchTerm) || LN.includes(searchTerm);
            });
        }
    },
    mounted() {
        this.getStudents();
    },
});