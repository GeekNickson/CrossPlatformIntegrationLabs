import React, { useEffect, useState } from 'react';
import { Col, Preloader, Row } from 'react-materialize';
import { Doctor } from '../model/doctor.model';
import { Service } from '../model/service.model';
import { ReactComponent as EmptyTable } from '../content/sad.svg';
import { axiosInstance } from '../util/axios';

function Table({ doctors, loading }: { doctors: Doctor[]; loading: boolean }) {
  const downloadJSON = () => {
    axiosInstance.get('/json').then((res) => {
      const url = window.URL.createObjectURL(new Blob([JSON.stringify(res.data)], { type: 'application/json' }));
      const a = document.createElement('a');
      document.body.appendChild(a);
      a.setAttribute('style', 'display: none');
      a.setAttribute('target', 'blank');
      a.href = url;
      a.download = 'file.json';
      a.click();
      window.URL.revokeObjectURL(url);
      a.remove();
    });
  };

  const [doctorsWorking, setDoctorsWorking] = useState(0);

  useEffect(
    () => setDoctorsWorking(doctors.reduce((total, current) => total + (current.onVacation ? 1 : 0), 0)),
    [doctors]
  );

  const content = doctors.map((doctor: Doctor, index: number) => (
    <tr key={index}>
      <td>{doctor.firstName}</td>
      <td>{doctor.lastName}</td>
      <td>{doctor.category}</td>
      <td>{doctor.specialty.name}</td>
      <td>
        {doctor.services.map((service: Service, i: number) => (
          <p key={i}>{service.name}</p>
        ))}
      </td>
      <td>
        {doctor.services.map((service: Service, i: number) => (
          <p key={i}>{service.price}</p>
        ))}
      </td>
      <td>{doctor.vacationStart}</td>
      <td>{doctor.vacationEnd}</td>
      <td>{doctor.onVacation ? 'Yes' : 'No'}</td>
    </tr>
  ));

  return (
    <Row>
      {loading ? (
        <Col s={6} m={4} l={4} className="center-align" offset="s3 m4 l4">
          <Preloader active color="blue" flashing={true} size="big" />
        </Col>
      ) : doctors.length > 0 ? (
        <>
          <Col s={12} m={12} l={12}>
            <table className="responsive-table highlight">
              <thead>
                <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Category</th>
                  <th>Specialty</th>
                  <th>Services</th>
                  <th>Prices</th>
                  <th>Vacation Start</th>
                  <th>Vacation End</th>
                  <th>On Vacation</th>
                </tr>
              </thead>
              <tbody>{content}</tbody>
              <tfoot>
                <tr>
                  <th>Doctors Working</th>
                  <td>{doctorsWorking}</td>
                </tr>
              </tfoot>
            </table>
          </Col>
          <Col s={8} m={4} l={4} className="mt-4">
            <button onClick={downloadJSON} className="waves-effect waves-light btn teal lighten-2">
              <i className="large material-icons right">file_download</i>
              Download JSON
            </button>
          </Col>
        </>
      ) : (
        <Col s={12} m={6} l={6} offset="m3 l3">
          <Row>
            <div className="center">
              <EmptyTable fill="#4db6ac" strokeWidth="1" />
            </div>
          </Row>
          <Row>
            <h2 className="center-align">There are no doctors:(</h2>
          </Row>
        </Col>
      )}
    </Row>
  );
}

export default Table;
