import React from 'react';

import { Card, CardBody, CardHeader, ListGroup, ListGroupItem } from 'reactstrap';

export default function PlayerInfo(props) {

  return (
    <Card className='profileInfo'>
      <CardHeader>
      <span className='profileText'>My Account</span> 
      </CardHeader>
      <ListGroup flush>
        <ListGroupItem>
          <span className='profileText' >Username:</span> {props.account}
        </ListGroupItem>
        <ListGroupItem>
        <span className='profileText'>Email:</span> {props.email}
        </ListGroupItem>
      </ListGroup>
    </Card>
  );
}
