package com.example.CarArchive.service;

import com.example.CarArchive.dto.PartRequest;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.exception.CarNotFoundException;
import com.example.CarArchive.exception.PartDeleteException;
import com.example.CarArchive.exception.PartNotFoundException;
import com.example.CarArchive.exception.PartSaveException;
import com.example.CarArchive.mapper.PartMapper;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final PartMapper partMapper;
    private final UserService userService;
    private final CarService carService;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, CarRepository carRepository, PartMapper partMapper, UserService userService, CarService carService) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.partMapper = partMapper;
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public List<PartResponse> getAllParts() {
        return partRepository.findAll().stream().map(partMapper::partToPartResponse).toList();
    }

    @Override
    public PartResponse getPartById(Long id) {
        Optional<PartResponse> optionalPart = partRepository.findById(id).map(partMapper::partToPartResponse);
        PartResponse partResponse = optionalPart.orElseThrow(() -> new PartNotFoundException("Part does not exist"));
        return partResponse;
    }

    @Override
    public PartResponse addNewPart(PartRequest partRequest) {

        Part part = partMapper.partRequestToPart(partRequest);

        try {
            partRepository.save(part);
        } catch (Exception e) {
            throw new PartSaveException("Part cannot be saved");
        }

        return new PartResponse(part.getId(), part.getName(), part.getMileage(), part.getDate(), part.getPartPrice(), part.getRepairPrice(), part.getNextExchange(), part.getCar().getId(), part.getCar().getUser().getId());
    }

    @Override
    public PartResponse updatePart(Long id, PartRequest partRequest) {

        Part partToUpdate = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part does not exist"));

        partToUpdate.setName(partRequest.getName());
        partToUpdate.setMileage(partRequest.getMileage());
        partToUpdate.setDate(partRequest.getDate());
        partToUpdate.setPartPrice(partRequest.getPartPrice());
        partToUpdate.setRepairPrice(partRequest.getRepairPrice());
        partToUpdate.setNextExchange(partRequest.getNextExchange());
        Car car = new Car();
        car.setId(partRequest.getCarId());

        User user = new User();
        user.setId(partRequest.getUserId());

        car.setUser(user);

        partToUpdate.setCar(car);

        try {
            partRepository.save(partToUpdate);
        } catch (Exception e) {
            throw new PartSaveException("Part cannot be updated");
        }

        return new PartResponse(partToUpdate.getId(), partToUpdate.getName(), partToUpdate.getMileage(), partToUpdate.getDate(), partToUpdate.getPartPrice(), partToUpdate.getRepairPrice(), partToUpdate.getNextExchange(), partToUpdate.getCar().getId(), partToUpdate.getCar().getUser().getId());
    }

    @Override
    public String deletePart(Long id) {

        Part optionalPart = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part does not exist"));

        try {
            partRepository.deleteById(id);
        } catch (Exception e) {
            throw new PartDeleteException("Part cannot be deleted");
        }

        return "Part " + id + " was deleted";
    }

    @Override
    public List<Object[]> getPartsToExchangeByMileage(int km, Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException("Car does not exist"));
        List<Object[]> objects = partRepository.findPartsByMileage(km, carId);
        return objects;
    }

    @Override
    public List<PartResponse> getAllPartsByLoggedUsername(String loggedUsername) {
        User user = userService.getUserByUsername(loggedUsername);
        Long userId = user.getId();

        return partRepository.findAll().stream()
                .filter(a -> a.getCar().getUser().getId() == userId)
                .map(partMapper::partToPartResponse)
                .toList();

//        return partRepository.findPartsByCarId(userId).stream()
//                .map(partMapper::partToPartResponse)
//                .toList();

    }

    @Override
    public PartResponse getPartByIdByLoggedUsername(Long id, String loggedUsername) {
        User user = userService.getUserByUsername(loggedUsername);
        Long userId = user.getId();

        Optional<PartResponse> optionalPart = partRepository.findById(id).map(partMapper::partToPartResponse);
        PartResponse partResponse = optionalPart.orElseThrow(() -> new PartNotFoundException("Part does not exist"));
        Long userIdCar = partResponse.getUserId();

        if (userId == userIdCar) {
            return partResponse;
        }
        throw new PartNotFoundException("You do not have permissions");
    }

    @Override
    public PartResponse addNewPartByLoggedUsername(PartRequest partRequest, String loggedUsername) {
        User user = userService.getUserByUsername(loggedUsername);
        Long userId = user.getId();

        partRequest.setUserId(userId);

        Part part = partMapper.partRequestToPart(partRequest);

        try {
            partRepository.save(part);
        } catch (Exception e) {
            throw new PartSaveException("Part cannot be saved");
        }

        return new PartResponse(part.getId(), part.getName(), part.getMileage(), part.getDate(), part.getPartPrice(), part.getRepairPrice(), part.getNextExchange(), part.getCar().getId(), part.getCar().getUser().getId());
    }

    @Override
    public PartResponse updatePartByLoggedUsername(Long id, PartRequest partRequest, String loggedUsername) {
        User user2 = userService.getUserByUsername(loggedUsername);
        Long userId = user2.getId();

        Part partToUpdate = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part does not exist"));
        Long userIdPart = partToUpdate.getCar().getUser().getId();

        if (userId == userIdPart) {

            partToUpdate.setName(partRequest.getName());
            partToUpdate.setMileage(partRequest.getMileage());
            partToUpdate.setDate(partRequest.getDate());
            partToUpdate.setPartPrice(partRequest.getPartPrice());
            partToUpdate.setRepairPrice(partRequest.getRepairPrice());
            partToUpdate.setNextExchange(partRequest.getNextExchange());
            Car car = new Car();
            car.setId(partRequest.getCarId());

            User user = new User();
            user.setId(userId);

            car.setUser(user);

            partToUpdate.setCar(car);

            try {
                partRepository.save(partToUpdate);
            } catch (Exception e) {
                throw new PartSaveException("Part cannot be updated");
            }

            return new PartResponse(partToUpdate.getId(), partToUpdate.getName(), partToUpdate.getMileage(), partToUpdate.getDate(), partToUpdate.getPartPrice(), partToUpdate.getRepairPrice(), partToUpdate.getNextExchange(), partToUpdate.getCar().getId(), partToUpdate.getCar().getUser().getId());
        }
        throw new PartNotFoundException("You do not have permissions");
    }

    @Override
    public String deletePartByLoggedUsername(Long id, String loggedUsername) {
        User user2 = userService.getUserByUsername(loggedUsername);
        Long userId = user2.getId();

        Part part = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part does not exist"));
        Long userIdPart = part.getCar().getUser().getId();

        if (userId == userIdPart) {
            try {
                partRepository.deleteById(id);
            } catch (Exception e) {
                throw new PartDeleteException("Part cannot be deleted");
            }

            return "Part " + id + " was deleted";
        }
        throw new PartNotFoundException("You do not have permissions");
    }


}
