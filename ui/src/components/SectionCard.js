import React from "react";
import logo from "../images/poem.jpg";
import { Redirect } from "react-router-dom";
import { Link } from "react-router-dom";

export default function SectionCard(props) {
  return (
    <div class="section-card">
      <div class="serial">{props.number}</div>
      <h2
        class="section-card-title"
        style={{ paddingTop: "0px", paddingBottom: "4px" }}
      >
        This is the title of article
      </h2>
      <div class="section-card-author">Rahul Mehra</div>
      <div class="section-card-info">Jun 24 . 4 min read</div>
    </div>
  );
}
