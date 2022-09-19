import React, { useState, useEffect } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import HospitalService from "../services/HospitalService";

const AddHospitalComponent = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [hospitalName, sethospitalName] = useState("");
  const [hospitalPhNum, sethospitalPhNum] = useState("");
  const [hospitalAddress, sethospitalAddress] = useState("");

  const saveOrUpdateHospital = (e) => {
    e.preventDefault();

    const hospital = {
      id,
      hospitalName,
      hospitalPhNum,
      hospitalAddress,
    };

    if (id) {
      HospitalService.updateHospital(id)
        .then((response) => {
          navigate("/hospitals");
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      HospitalService.createHospital()
        .then((response) => {
          console.log(response.data);

          navigate("/hospitals");
        })
        .catch((error) => {
          console.log(error);
        });
    }
  };

  useEffect(() => {
    HospitalService.getHospitalById(id)
      .then((response) => {
        sethospitalName(response.data.hospitalName);
        sethospitalPhNum(response.data.hospitalPhNum);
        sethospitalAddress(response.data.hospitalAddress);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const title = () => {
    if (id) {
      return <h2 className="text-center">Update Hospital</h2>;
    } else {
      return <h2 className="text-center">Add Hospital</h2>;
    }
  };

  return (
    <div>
      <br />
      <br />
      <div className="container">
        <div className="row">
          <div className="card col-md-6 offset-md-3 offset-md-3">
            {title()}
            <div className="card-body">
              <form>
                <div className="form-group mb-2">
                  <label className="form-label"> Hospital name:</label>
                  <input
                    type="text"
                    placeholder="Enter hospital name"
                    name="hospitalname"
                    className="form-control"
                    value={hospitalName}
                    onChange={(e) => sethospitalName(e.target.value)}
                  ></input>
                </div>

                <div className="form-group mb-2">
                  <label className="form-label">Phone no.:</label>
                  <input
                    type="text"
                    placeholder="Enter hospital phone no."
                    name="phone"
                    className="form-control"
                    value={hospitalPhNum}
                    onChange={(e) => sethospitalPhNum(e.target.value)}
                  ></input>
                </div>

                <div className="form-group mb-2">
                  <label className="form-label">Address:</label>
                  <input
                    type="text"
                    placeholder="Enter hospital address."
                    name="address"
                    className="form-control"
                    value={hospitalAddress}
                    onChange={(e) => sethospitalAddress(e.target.value)}
                  ></input>
                </div>

                <button
                  className="btn btn-success"
                  onClick={(e) => saveOrUpdateHospital(e)}
                >
                  Submit
                </button>
                <Link to="/hospitals" className="btn btn-danger">
                  Cancel
                </Link>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddHospitalComponent;
