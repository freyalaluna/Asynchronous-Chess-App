import React, { useState } from 'react';

import { Collapse, Badge, Card, Button, CardHeader, ListGroup, ListGroupItem, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';

export default function PlayerInfo(props) {
  const [modal, setModal] = useState(false);
  const [isCollapseOpen, setIsCollapseOpen] = useState(false);

  const toggleModal = () => setModal(!modal);
  const toggleCollapse = () => setIsCollapseOpen(!isCollapseOpen);

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
        <ListGroupItem>
          <Button color="danger" size="sm" outline onClick={toggleCollapse} >
            <Badge color="danger">!</Badge>
            &nbsp;Danger Zone&nbsp;
            <Badge color="danger">!</Badge>
            </Button>
          <Collapse isOpen={isCollapseOpen} >
            <br/>
            <Button color="danger" size="sm" onClick={toggleModal} >
              Delete My Account
            </Button>
            <DeleteAccountModal isOpen={modal} toggle={toggleModal} accountActions={props.accountActions} setShowProfile={props.setShowProfile} />
          </Collapse>
        </ListGroupItem>
      </ListGroup>
    </Card>
  );
}

function DeleteAccountModal(props) {
  return (
    <Modal isOpen={props.isOpen} toggle={props.toggle} >
      <ModalHeader toggle={props.toggle}>Are you sure?</ModalHeader>
      <ModalBody>
        Do you really want to delete your account? This action CANNOT be undone.
      </ModalBody>
      <ModalFooter>
        <Button color="primary" onClick={props.toggle}>
          Cancel
        </Button>
        <Button color="danger" outline onClick={() => {
          props.accountActions.sendDeleteRequest();
          props.setShowProfile(false);
          props.toggle();
        }}>
          Yes, I'm Sure
        </Button>
      </ModalFooter>
    </Modal>
  );
}
