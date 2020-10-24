import React from "react";
import SectionCard from "./SectionCard";

const Sidebar = () => {
  return (
    <div class="side-bar" style={{ marginLeft: "10px", textAlign: "left" }}>
      <h2>Popular on PoetInsta</h2>
      <hr style={{ margin: "0px 0px" }}></hr>
      <div style={{ marginBottom: "34px", paddingTop: "32px" }}>
        <SectionCard number="01" />
        <SectionCard number="02" />
        <SectionCard number="03" />
        <SectionCard number="04" />
      </div>
    </div>
  );
};

export default Sidebar;
