import React, { useState } from 'react';

// Sample Schedule component
const Schedule = () => {
  // State to manage the fields
  const [fields, setFields] = useState([
    { label: 'Add Assignment', value: '' },
    { label: 'Add Evaluation', value: '' },
    { label: 'Mark Summary', value: '' },
    { label: 'Approval Task', value: '' }
  ]);

  // Function to handle adding a new field
  const addField = (label) => {
    const newField = {
      label,
      value: ''
    };
    setFields([...fields, newField]);
  };

  return (
    <div>
      <h2>Schedule</h2>
      {/* Render existing fields */}
      <ul>
        {fields.map((field, index) => (
          <li key={index}>
            <label>{field.label}</label>
            <input 
              type="text" 
              value={field.value} 
              onChange={(e) => {
                // Update the value of the field when it changes
                const updatedFields = [...fields];
                updatedFields[index].value = e.target.value;
                setFields(updatedFields);
              }} 
            />
          </li>
        ))}
      </ul>
      {/* Buttons to add specific fields */}
      <button onClick={() => addField('Add Assignment')}>Add Assignment</button>
      <button onClick={() => addField('Add Evaluation')}>Add Evaluation</button>
      <button onClick={() => addField('Mark Summary')}>Mark Summary</button>
      <button onClick={() => addField('Approval Task')}>Approval Task</button>
    </div>
  );
};

export default Schedule;
