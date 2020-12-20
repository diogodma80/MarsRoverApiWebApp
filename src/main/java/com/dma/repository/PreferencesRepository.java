package com.dma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface PreferencesRepository extends JpaRepository<MarsPreferences, Long> {

	MarsPreferences findByUserId(Long userId);;

}
