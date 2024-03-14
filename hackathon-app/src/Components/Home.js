import React from "react";
import { NavLink } from "react-router-dom";

const Home = () => {
  return (
    <div>
      <h1>Home Page</h1>
      <NavLink to="/assignTask">AssignTask</NavLink>
    </div>
  );
};

export default Home;
