import React, { useState } from 'react';
import './App.css';
import Uploader from './components/Uploader';
import Table from './components/Table';
import { Doctor } from './model/doctor.model';
import { Error } from './model/error.model';
import ErrorComponent from './components/Error';

function App() {
  const [doctors, setDoctors] = useState<Doctor[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  return (
    <div className="container">
      <h1 className="center-align">Doctors</h1>
      <Uploader setDoctors={setDoctors} setLoading={setLoading} setError={setError} />
      {error ? <ErrorComponent error={error} /> : <Table doctors={doctors} loading={loading} />}
    </div>
  );
}

export default App;
