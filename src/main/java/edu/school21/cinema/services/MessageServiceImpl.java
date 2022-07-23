package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    @Override
    public Optional<Message> get(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public void add(Message entity) {
        messageRepository.save(entity);
    }

    @Override
    public List<Message> getHistory(Long filmId) {
        return messageRepository.getFirst20ByMovie_Id( filmId);
    }
}
