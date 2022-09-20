import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import HospitalService from "../services/HospitalService";

const ListHospitalComponent = () => {
  const [hospital, setHospital] = useState([]);

  useEffect(() => {
    getAllHospitals();
  }, []);

  const getAllHospitals = () => {
    HospitalService.getAllHospitals()
      .then((response) => {
        setHospital(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleteHospital = (hospitalId) => {
    HospitalService.deleteHospital(hospitalId)
      .then((response) => {
        getAllHospitals();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <div className="container">
      <h2 className="text-center"> Hospital details </h2>
      {/* <Link to="/add-hospital" className="btn btn-primary mb-2">
        {" "}
        Add hospital{" "}
      </Link> */}
      <table className="table table-bordered table-striped">
        <thead>
          <th> Id </th>
          <th> Name </th>
          <th> Phone no. </th>
          <th> Address </th>
          <th> Actions </th>
        </thead>
        <tbody>
          {hospital.map((hospital) => (
            <tr key={hospital.hospitalId}>
              <td>{hospital.hospitalId} </td>
              <td> {hospital.hospitalName} </td>
              <td>{hospital.hospitalPhNum}</td>
              <td>{hospital.hospitalAddress}</td>
              <td>
                {/* <Link
                  className="btn btn-info"
                  to={`/edit-hospital/${hospital.hospitalId}`}
                >
                  Update
                </Link> */}
                <button
                  className="btn btn-danger"
                  onClick={() => deleteHospital(hospital.hospitalId)}
                  style={{ marginLeft: "10px" }}
                >
                  {" "}
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListHospitalComponent;
