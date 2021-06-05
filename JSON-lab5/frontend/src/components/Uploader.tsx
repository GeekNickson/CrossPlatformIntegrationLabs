import React from 'react';
import { useCallback, useMemo } from 'react';
import { DropzoneRootProps, useDropzone } from 'react-dropzone';
import { Col, Row } from 'react-materialize';
import { Doctor } from '../model/doctor.model';
import { Error } from '../model/error.model';
import { axiosInstance } from '../util/axios';

const config = {
  headers: {
    'content-type': 'multipart/form-data',
  },
};

const baseStyle = {
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  cursor: 'pointer',
  padding: '20px',
  borderWidth: 5,
  borderRadius: 10,
  borderColor: '#b0bec5',
  borderStyle: 'dashed',
  backgroundColor: '#ffffff',
  color: '#4db6ac',
  fontSize: '3rem',
  transition: 'border .3s ease-in-out',
};

const activeStyle = {
  borderColor: '#81d4fa',
};

const acceptStyle = {
  borderColor: '#80cbc4',
};

const rejectStyle = {
  borderColor: '#ef9a9a',
};

function Uploader({
  setDoctors,
  setLoading,
  setError,
}: {
  setDoctors: Function;
  setLoading: Function;
  setError: Function;
}) {
  const formRequest = (file: File): FormData => {
    const data = new FormData();
    data.append('file', file);
    return data;
  };

  const getDoctors = useCallback(
    async (file: File) => {
      if (file) {
        setLoading(true);
        try {
          const res = await axiosInstance.post<Doctor[]>('/json', formRequest(file), config);
          setError(null);
          setDoctors(res.data);
          setLoading(false);
        } catch (error) {
          setError(new Error(error.response.status, error.response.data));
          setDoctors([]);
          setLoading(false);
        }
      }
    },
    [setDoctors, setLoading, setError]
  );

  const onDrop = useCallback((files: File[]) => getDoctors(files[0]), [getDoctors]);

  const { getRootProps, getInputProps, isDragActive, isDragAccept, isDragReject } = useDropzone({
    onDrop,
    accept: 'text/xml',
    multiple: false,
  });

  const style: DropzoneRootProps = useMemo(
    () => ({
      ...baseStyle,
      ...(isDragActive ? activeStyle : {}),
      ...(isDragAccept ? acceptStyle : {}),
      ...(isDragReject ? rejectStyle : {}),
    }),
    [isDragActive, isDragReject, isDragAccept]
  );

  return (
    <Row>
      <Col s={8} m={6} l={6} offset="s2 m3 l3">
        <div {...getRootProps({ style })}>
          <input {...getInputProps()} />
          <div>
            <i className="large material-icons">file_upload</i>
          </div>
        </div>
      </Col>
    </Row>
  );
}

export default Uploader;
