import axios from "axios";
import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./AssignTask.css"; // Import CSS file for custom styles
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const AssignTask = () => {
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedCourseId, setSelectedCourseId] = useState(null);
  const [courseSubjects, setCourseSubjects] = useState([]);
  const [selectedSubjectId, setSelectedSubjectId] = useState(null);
  const [groups, setGroups] = useState([]);
  const [loadingGroups, setLoadingGroups] = useState(false);
  const [staffNames, setStaffNames] = useState([]);
  const [selectedStaff, setSelectedStaff] = useState(null);
  const [selectedStaffId, setSelectedStaffId] = useState(null); // State to store selected staff id
  const [selectedExamType, setSelectedExamType] = useState(null);
  const [fromDate, setFromDate] = useState(new Date());
  const [tillDate, setTillDate] = useState(null);
  const [selectedDate, setSelectedDate] = useState(null); // State to store selected date
  const [selectedGroup, setSelectedGroup] = useState(null);

  // Function to handle the form submission
  const handleAssignTask = async () => {
    try {
      // Format the dates
      const formattedFromDate = fromDate.toLocaleDateString("en-CA");
      const formattedTillDate = tillDate
        ? tillDate.toLocaleDateString("en-CA")
        : null;

      // Construct the data object to send
      const data = {
        subject: {
          sub_id: selectedSubjectId,
        },
        evaluationType: selectedExamType,
        fromDate: formattedFromDate,
        validTill: formattedTillDate,
        grup: selectedGroup,
        userId: selectedStaffId,
      };
      console.log("Data to Server: ", data);
      // Send POST request to the server
      const response = await axios.post(
        "http://localhost:8080/evaluation/evaluation-schedule",
        data
      );

      // Handle success response
      console.log("Data sent successfully:", response.data);
      // Display success message
      toast.success("Task Assigned Successfully");
      // Reset all fields
      setSelectedCourseId(null);
      setSelectedSubjectId(null);
      setGroups([]);
      setSelectedStaff(null);
      setSelectedStaffId(null);
      setSelectedExamType(null);
      setFromDate(new Date());
      setTillDate(null);
      setSelectedGroup(null);
    } catch (error) {
      // Handle error
      console.error("Error sending data:", error);
    }
  };

  useEffect(() => {
    const fetchCourses = async () => {
      try {
        const response = await axios.get("http://localhost:8080/courses");
        setCourses(response.data);
        setLoading(false);
      } catch (error) {
        console.log(error);
        setLoading(false);
      }
    };
    fetchCourses();
  }, []);

  useEffect(() => {
    const fetchGroups = async () => {
      if (selectedSubjectId) {
        try {
          setLoadingGroups(true);
          const response = await axios.get(
            "http://localhost:8080/students/getAllGroups"
          );
          const selectedGroup = response.data.selectedGroup;
          // Set the selected group state
          setSelectedGroup(selectedGroup);
          setGroups(response.data);
          setLoadingGroups(false);
          console.log("Groups:", response.data);
        } catch (error) {
          console.log(error);
          setLoadingGroups(false);
        }
      }
    };
    fetchGroups();
  }, [selectedSubjectId]);

  useEffect(() => {
    const fetchCourseSubjects = async () => {
      if (selectedCourseId) {
        try {
          const response = await axios.get(
            `http://localhost:8080/courseSubject/by-course?courseId=${selectedCourseId}`
          );
          setCourseSubjects(response.data);
        } catch (error) {
          console.log(error);
        }
      }
    };
    fetchCourseSubjects();
  }, [selectedCourseId]);

  useEffect(() => {
    const fetchStaffNames = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/users/allStaff"
        );
        setStaffNames(response.data);
        console.log("Staff Names:", response.data);
      } catch (error) {
        console.log(error);
      }
    };
    fetchStaffNames();
  }, []);

  const handleCourseChange = (event) => {
    setSelectedCourseId(event.target.value);
    // Reset selected subject, groups, staff, and exam type when a new course is selected
    setSelectedSubjectId(null);
    setSelectedStaff(null);
    setSelectedExamType(null);
    setGroups([]);
  };

  const handleSubjectChange = (event) => {
    setSelectedSubjectId(event.target.value);
    // Reset selected groups, staff, and exam type when a new subject is selected
    setSelectedStaff(null);
    setSelectedExamType(null);
  };
  const handleGroupChange = (event) => {
    setSelectedGroup(event.target.value);
  };

  const handleStaffChange = (event) => {
    const selectedStaffName = event.target.value;
    setSelectedStaff(selectedStaffName);

    // Find the staff object with the selected name and extract its id
    const selectedStaffObject = staffNames.find(
      (staff) => staff.staffName === selectedStaffName
    );
    if (selectedStaffObject) {
      setSelectedStaffId(selectedStaffObject.id); // Set selected staff id in state
    }
  };

  const handleExamTypeChange = (event) => {
    setSelectedExamType(event.target.value);
  };

  const handleFromDateClick = () => {
    const fromDate = new Date();
    setFromDate(fromDate);
    console.log("From Date:", fromDate);
  };
  const handleTillDateSelect = (date) => {
    setTillDate(date);
  };
  const handleTillDateClick = () => {
    const tillDate = new Date();
    setTillDate(tillDate);
    console.log("Till Date:", tillDate);
  };
  const handleDateSelect = (date) => {
    setTillDate(date);
  };

  return (
    <div className="container mt-5">
      <div className="form-group">
        <label htmlFor="selectCourse">Select Course:</label>
        <select
          id="selectCourse"
          name="selectCourse"
          className="form-control"
          onChange={handleCourseChange}
        >
          <option value="">Select Course</option>
          {loading ? (
            <option value="Select Course">Loading...</option>
          ) : (
            courses.map((course) => (
              <option key={course.courseId} value={course.courseId}>
                {course.c_name}
              </option>
            ))
          )}
        </select>
      </div>
      <div className="form-group">
        <label htmlFor="selectSubject">Select Subject:</label>
        <select
          id="selectSubject"
          name="selectSubject"
          className="form-control"
          onChange={handleSubjectChange}
          disabled={!selectedCourseId}
        >
          <option value="">Select Subject</option>
          {courseSubjects &&
            courseSubjects.length > 0 &&
            courseSubjects.map((subject) => (
              <option key={subject.sub_id} value={subject.sub_id}>
                {subject.subject}
              </option>
            ))}
        </select>
      </div>
      <div className="form-group">
        <label htmlFor="selectGroup">Select Group:</label>
        <select
          id="selectGroup"
          name="selectGroup"
          className="form-control"
          onChange={handleGroupChange}
          disabled={!selectedSubjectId}
        >
          <option value="" disabled={!selectedSubjectId}>
            {selectedSubjectId ? "Select Group" : "Select Subject first"}
          </option>
          {loadingGroups ? (
            <option value="" disabled>
              Loading...
            </option>
          ) : (
            groups &&
            groups.length > 0 &&
            groups.map((group, index) => (
              <option key={index} value={group}>
                {group}
              </option>
            ))
          )}
        </select>
      </div>
      <div className="form-group">
        <label htmlFor="selectStaff">Select Staff:</label>
        <select
          id="selectStaff"
          name="selectStaff"
          className="form-control"
          onChange={handleStaffChange}
          disabled={!selectedSubjectId}
        >
          <option value="">Select Staff</option>
          {staffNames &&
            staffNames.length > 0 &&
            staffNames.map((staff) => (
              <option key={staff.id} value={staff.staffName}>
                {staff.staffName}
              </option>
            ))}
        </select>
      </div>
      <div className="form-group">
        <label>Select Exam Type:</label>
        <div>
          <input
            type="radio"
            id="internal-exam-1"
            name="examType"
            value="Internal Exam-1"
            onChange={handleExamTypeChange}
          />
          <label htmlFor="internal-exam-1">Internal Exam-1</label>
        </div>
        <div>
          <input
            type="radio"
            id="internal-exam-2"
            name="examType"
            value="Internal Exam-2"
            onChange={handleExamTypeChange}
          />
          <label htmlFor="internal-exam-2">Internal Exam-2</label>
        </div>
        <div>
          <input
            type="radio"
            id="lab-exam"
            name="examType"
            value="Lab Exam"
            onChange={handleExamTypeChange}
          />
          <label htmlFor="lab-exam">Lab Exam</label>
        </div>
        <div>
          <input
            type="radio"
            id="theory-exam"
            name="examType"
            value="Theory"
            onChange={handleExamTypeChange}
          />
          <label htmlFor="theory-exam">Theory</label>
        </div>
      </div>
      <div className="form-group">
        <div className="button-container">
          <button className="btn btn-primary" onClick={handleFromDateClick}>
            From Date: {fromDate.toLocaleDateString("en-CA")}
          </button>
          <button className="btn btn-primary">
            <span className="date-display">
              {tillDate && (
                <span>Till Date: {tillDate.toLocaleDateString("en-CA")}</span>
              )}
            </span>
            <DatePicker
              selected={tillDate}
              onSelect={handleTillDateSelect}
              onChange={handleTillDateSelect}
              value="Till Date"
              minDate={new Date()}
              placeholderText="Till Date"
            />
          </button>
        </div>
      </div>
      <button className="btn btn-primary" onClick={handleAssignTask}>
        Assign Task
      </button>
    </div>
  );
};

export default AssignTask;
