package org.example.aletheiacares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CategoryRepository categoryRepository;
    private final SpiritualGiftRepository spiritualGiftRepository;

    @Autowired
    public UserService(
            UserRepository userRepository,
            UserMapper userMapper,
            CategoryRepository categoryRepository,
            SpiritualGiftRepository spiritualGiftRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.categoryRepository = categoryRepository;
        this.spiritualGiftRepository = spiritualGiftRepository;
    }

    public UserDto createUser(UserDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }

        User user = userMapper.toEntity(dto);

        // Set categories by IDs
        List<Category> categories = new ArrayList<>();
        if (dto.getCategoryIds() != null) {
            categories = categoryRepository.findAllById(dto.getCategoryIds());
        }
        user.setCategories(categories);

        // Set gifts by IDs
        Set<SpiritualGift> gifts = new HashSet<>();
        if (dto.getGiftIds() != null) {
            gifts.addAll(spiritualGiftRepository.findAllById(dto.getGiftIds()));
        }
        user.setGifts(gifts);

        User savedUser = userRepository.save(user);
        return dto;
    }

    public Optional<UserDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    // Optionally, add a method to get all users as DTOs:
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        for (User user : users) {
            dtos.add(userMapper.toDto(user));
        }
        return dtos;
    }

    public List<UserDto> findUserByFirstNameAndLastName(String firstName, String lastName) {
        return userRepository.findUserByFirstNameAndLastName(firstName, lastName)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
