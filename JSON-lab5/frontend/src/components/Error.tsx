import React from 'react';
import { Col, Row } from 'react-materialize';
import { Error } from '../model/error.model';

function ErrorComponent({ error }: { error: Error }) {
  return (
    <Row>
      <Col s={8} m={6} l={6} offset="s2 m3 l3">
        <div className="pink lighten-4 error">
          <p>
            {error.status}: {error.message}
          </p>
        </div>
      </Col>
    </Row>
  );
}

export default ErrorComponent;
