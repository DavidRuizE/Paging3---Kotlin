package com.david.paging3implementation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import com.david.paging3implementation.data.response.RickRepository
import com.david.paging3implementation.presentation.model.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class RickListViewModel @Inject constructor(rickRepository: RickRepository): ViewModel(){

    val characters: Flow<PagingData<CharacterModel>> = rickRepository.getAllCharacters()

}